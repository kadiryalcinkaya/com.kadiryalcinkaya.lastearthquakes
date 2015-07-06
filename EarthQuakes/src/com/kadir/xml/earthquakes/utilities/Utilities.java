package com.kadir.xml.earthquakes.utilities;

import android.content.Context;
import android.content.Intent;

public class Utilities {
	
	private Utilities(){}
	
	public static void clearAndStartNewActivity(Context packageContext, Class<?> cls){
		Intent intent = new Intent(packageContext, cls);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
		packageContext.startActivity(intent);
	}

}
