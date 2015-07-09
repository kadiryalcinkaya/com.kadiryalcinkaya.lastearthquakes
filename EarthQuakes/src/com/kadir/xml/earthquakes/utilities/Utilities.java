package com.kadir.xml.earthquakes.utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

public class Utilities {
	
	private Utilities(){}
	
	public static void showAlertDialog(Context context, String title, String message, DialogInterface.OnClickListener listener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton(android.R.string.ok, listener);
		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}
	
	public static void clearAndStartNewActivity(Context packageContext, Class<?> cls){
		Intent intent = new Intent(packageContext, cls);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
		packageContext.startActivity(intent);
	}

}
