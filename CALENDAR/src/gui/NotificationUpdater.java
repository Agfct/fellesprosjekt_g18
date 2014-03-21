package gui;

public class NotificationUpdater implements Runnable {

	@Override
	public void run() {
		
//		while (true){
//			if(MainWindow.getLeftView() != null){
//				update();
//			}else {
//				break;
//			}
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
	}

	public void update() {
		int[] notifications = new int[2];
		if (MainWindow.getUser() != null){
			notifications = MainWindow.getRequestHandler().getNotifications(MainWindow.getUser());
			if (MainWindow.getLeftView() != null){
				MainWindow.getLeftView().setNrOfNewAppointmentsNotification(notifications[0]);
				MainWindow.getLeftView().setNrOfeditAppointmentNotification(notifications[1]);
			}
		}
	}
}
