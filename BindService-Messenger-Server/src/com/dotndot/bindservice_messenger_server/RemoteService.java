package com.dotndot.bindservice_messenger_server;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class RemoteService extends Service {

	static final int MSG_CONNECT = 1;
	static final int MSG_DISCONNECT = 2;
	static final int MSG_ISCONNECT = 3;
	final Messenger mMessenger = new Messenger(new RemoteHandler());

	@Override
	public void onCreate() {
		Log.e("RemoteService", "onCreate");
		super.onCreate();
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.e("RemoteService", "onBind");
		return mMessenger.getBinder();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.e("RemoteService", "onUnbind");
		return super.onUnbind(intent);
	}

	class RemoteHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_CONNECT:
				Log.e("RemoteService", "connect");
				try {
					msg.replyTo.send(Message.obtain(null, MSG_CONNECT, 0, 0));
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				break;
			case MSG_DISCONNECT:
				Log.e("RemoteService", "disconnect");
				break;
			case MSG_ISCONNECT:
				Log.e("RemoteService", "isConnect");
				break;
			default:
				super.handleMessage(msg);
			}
		}
	}

}
