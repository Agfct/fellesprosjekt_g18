package gui;

public class NotificationUpdater implements Runnable {

	@Override
	public void run() {
		int[] notifications = new int[2];
		while (true){
			if (MainWindow.getUser() != null){
				notifications = MainWindow.getRequestHandler().getNotifications(MainWindow.getUser());
				if (MainWindow.getLeftView() != null){
					MainWindow.getLeftView().setNrOfNewAppointmentsNotification(notifications[1]);
					MainWindow.getLeftView().setNrOfeditAppointmentNotification(notifications[0]);
				}else{
					break;
				}
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
