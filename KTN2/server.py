'''
KTN-project 2013 / 2014
Very simple server implementation that should serve as a basis
for implementing the chat server
'''
import SocketServer
import json
import re

'''
The RequestHandler class for our server.

It is instantiated once per connection to the server, and must
override the handle() method to implement communication to the
client.
'''

clients = []
usernames = []
backlog = []
class ClientHandler(SocketServer.BaseRequestHandler):
    def handle(self):
        # Get a reference to the socket object
        self.connection = self.request
        # Get the remote ip adress of the socket
        self.ip = self.client_address[0]
        # Get the remote port number of the socket
        self.port = self.client_address[1]
        print 'Client connected @' + self.ip + ':' + str(self.port)
        # Wait for data from the client
        while True:
            json_request = self.connection.recv(1024).strip()
            # Check if the data exists
            # (recv could have returned due to a disconnect)
            if json_request:
                request = json.loads(json_request)

                # logging in
                if (request['request'] == 'login'):
                    username = request['username']
                    if (username in usernames):
                        response = {
                                    'response':'login',
                                    'error': 'Name already taken!',
                                    'username':username
                                    }
                    elif (re.match('\W+', username)):
                        response = {
                                    'response':'login',
                                    'error':'Invalid username!',
                                    'username':username
                                    }
                    else:
                        response = {
                                    'response':'login',
                                    'username':username,
                                    'messages':backlog
                                    }
                        clients.append(self.connection)
                        usernames.append(username)
                    json_response = json.dumps(response)
                    self.connection.sendall(json_response)


                # sending messages
                if (request['request'] == 'message'):
                    message = request['message']
                    print message
                    backlog.append(message)
                    username = usernames[clients.index(self.connection)]
                    response = {
                                'response':'message',
                                'message': username + ': ' + message
                                }
                    json_response = json.dumps(response)
                    for client in clients:
                        client.sendall(json_response)

                # logout
                if (request['request'] == 'logout'):
                    if (self.connection in clients):
                        username = usernames[clients.index(self.connection)]
                        response = {
                                    'response':'logout',
                                    'username':username
                                    }
                    else:
                        response = {
                                    'response':'logout',
                                    'error':'Not logged in!'
                                    }
                    json_response = json.dumps(response)
                    self.connection.sendall(json_response)
                    usernames.remove(username)
                    clients.remove(self.connection)
                
            else:
                print 'Client disconnected!'
                break

'''
This will make all Request handlers being called in its own thread.
Very important, otherwise only one client will be served at a time
'''


class ThreadedTCPServer(SocketServer.ThreadingMixIn, SocketServer.TCPServer):
    pass

if __name__ == "__main__":
    HOST = 'localhost'
    PORT = 9999

    # Create the server, binding to localhost on port 9999
    server = ThreadedTCPServer((HOST, PORT), ClientHandler)

    # Activate the server; this will keep running until you
    # interrupt the program with Ctrl-C
    server.serve_forever()
