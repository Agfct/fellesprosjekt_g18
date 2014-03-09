'''
KTN-project 2013 / 2014
'''
import socket
from MessageWorker import ReceiveMessageWorker
import json


class Client(object):

    def __init__(self):
        self.connection = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.logged_in = False

    def start(self, host, port):
        self.connection.connect((host, port))
        messageWorker = ReceiveMessageWorker(self, self.connection)
        messageWorker.start()
        self.login()

        while True:
            message = raw_input()
            if (self.logged_in == False):
                continue
            if (message == '/logout'):
                request = {
                            'request':'logout'
                            }
            else:
                request = {
                            'request':'message',
                            'message':message
                            }
            json_request = json.dumps(request)
            self.send(json_request)

    def message_received(self, json_response, connection):
        response = json.loads(json_response)
        if (response['response'] == 'message'):
            message = response['message']
            print message

        elif (response['response'] == 'login'):
            if ('error' in response.keys()):
                print response['error'] + " Press enter to try again."
                self.login()
            else:
                print 'Logged in!'
                self.logged_in = True
                print 'Backlog:\n'
                for backlog in response['messages']:
                    print backlog

        elif (response['response'] == 'logout'):
            if ('error' in response.keys()):
                print response['error']
            else:
                print 'Logged out! Press enter to log back in.'
                self.logged_in = False
                self.login()
    
    def login(self):
        username = raw_input('Choose username: ')
        request = {
                    'request':'login',
                    'username':username
                    }
        json_request = json.dumps(request)
        self.send(json_request)

    def connection_closed(self, connection):
        pass

    def send(self, data):
        self.connection.sendall(data)

    def force_disconnect(self):
        pass


if __name__ == "__main__":
    host = raw_input('Enter ip of server: ')
    port = raw_input('Enter port number: ')
    client = Client()
    client.start(host, int(port))
