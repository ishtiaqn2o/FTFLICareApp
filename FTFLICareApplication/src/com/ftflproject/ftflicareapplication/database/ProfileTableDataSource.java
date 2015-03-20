package com.ftflproject.ftflicareapplication.database;

import java.util.ArrayList;
import java.util.List;

import com.ftflproject.ftflicareapplication.model.DoctorModel;
import com.ftflproject.ftflicareapplication.model.DoctorModel;
import com.ftflproject.ftflicareapplication.model.ProfileModel;
import com.ftflproject.ftflicareapplication.model.VaccinationModel;







import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ProfileTableDataSource {

	private DatabaseHelperClass dbHelper;

	public ProfileTableDataSource(Context context) {
		dbHelper = new DatabaseHelperClass(context);
	}

	// Create doctor into table
	public long createProfile(ProfileModel profileObj) {

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();

	
		contentValues.put("name", profileObj.getmName());
		contentValues.put("gender", profileObj.getmGender());
		contentValues.put("age", profileObj.getmAge());
		contentValues.put("weight", profileObj.getmWeight());
		contentValues.put("height", profileObj.getmHeight());

		return db.insert(DatabaseHelperClass.PROFILE_TABLE_NAME, null,
				contentValues);

	}

	// this function performs to edit an specific doctor from doctorLIst by id

	public long editProfile(Integer id, ProfileModel profileObj) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		ContentValues contentValues = new ContentValues();

		contentValues.put("name", profileObj.getmName());
		contentValues.put("gender", profileObj.getmGender());
		contentValues.put("age", profileObj.getmAge());
		contentValues.put("weight", profileObj.getmWeight());
		contentValues.put("height", profileObj.getmHeight());

		return db.update("profile", contentValues, "id = ? ",
				new String[] { Integer.toString(id) });
		// return true;
	}

	// return all diet list
	public ArrayList<ProfileModel> getAllProfile() {
		ProfileModel profilemodelObj = null;
		ArrayList<ProfileModel> allProfileList = new ArrayList<ProfileModel>();

		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor result = db.rawQuery("select * from profile", null);
		if (result.moveToFirst()) {
			do {

				
				int profileId = result.getInt(0);
				
				String name = result.getString(1);
				String gender = result.getString(2);
				String age = result.getString(4);
				String weight = result.getString(5);
				String height = result.getString(6);

				profilemodelObj = new ProfileModel(name, gender, age, weight, height);
				profilemodelObj.setmId(profileId);
				
				allProfileList.add(profilemodelObj);
			} while (result.moveToNext());

		}
		return allProfileList;
	}
	
	public ProfileModel getProfileById(int id) {

		ProfileModel doctorsProfile = null;
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor result = db.rawQuery("select * from profile where id='" + id + "'",
				null);
		if (result.moveToFirst()) {
			do {

				int profileId = result.getInt(0);
				
				String name = result.getString(1);
				String gender = result.getString(2);
				String age = result.getString(4);
				String weight = result.getString(5);
				String height = result.getString(6);

				doctorsProfile = new ProfileModel(name, gender, age, weight, height);
				doctorsProfile.setmId(profileId);

			} while (result.moveToNext());

		}
		return doctorsProfile;
	}
	
	
	 public List<String> getAllNames(){
	        List<String> names = new ArrayList<String>();
	          
	        SQLiteDatabase db = dbHelper.getReadableDatabase();
			Cursor result = db.rawQuery("select * from doctor ",
					null);
			
			if (result.moveToFirst()) {
				do {

					
					names.add(result.getString(2));
					


				} while (result.moveToNext());

			}
			
			return names;
			
			
	    }
	
	

}
