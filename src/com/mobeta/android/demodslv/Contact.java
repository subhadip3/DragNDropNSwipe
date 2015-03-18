package com.mobeta.android.demodslv;

// class representing each item in db
public class Contact
{

	// private variables
	int _id;

	String _name;

	String _phone_number;

	int _new_pos;

	// Empty constructor
	public Contact()
	{

	}

	// constructor
	public Contact(int id, String name, String _phone_number, int _new_pos)
	{
		this._id = id;
		this._name = name;
		this._phone_number = _phone_number;
		this._new_pos = _new_pos;
	}
	public Contact(int id,  int _new_pos)
	{
		this._id = id;
		
		this._new_pos = _new_pos;
	}

	// constructor
	public Contact(String name, String _phone_number)
	{
		this._name = name;
		this._phone_number = _phone_number;
	}

	// getting ID
	public String getID()
	{
		return "" + this._id;
	}

	// setting id
	public void setID(int id)
	{
		this._id = id;
	}

	// getting name
	public String getName()
	{
		return this._name;
	}

	// setting name
	public void setName(String name)
	{
		this._name = name;
	}

	// getting phone number
	public String getPhoneNumber()
	{
		return this._phone_number;
	}

	// setting phone number
	public void setPhoneNumber(String phone_number)
	{
		this._phone_number = phone_number;
	}

	public String getNewPos()
	{
		return "" + this._new_pos;
	}

	public void setNewPos(int _new_pos)
	{
		this._new_pos = _new_pos;
	}

}
