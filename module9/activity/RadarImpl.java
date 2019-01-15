package sef.module9.activity;

import javax.print.attribute.standard.Copies;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of a Radar 
 * 
 *
 */
public class RadarImpl implements Radar{


	private List<RadarContact> radarContacts;
	/**
	 *  Constructs a new Radar 
	 */
	public RadarImpl(){
		radarContacts = new LinkedList<RadarContact>();
	}
	
	
	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#addContact(sef.module8.activity.RadarContact)
	 */
	public RadarContact addContact(RadarContact contact) {
		RadarContact currentContact = getContact(contact.getContactID());
		if (currentContact == null){
			radarContacts.add(contact);
		}
		else{
			radarContacts.add(radarContacts.indexOf(currentContact),contact);
		}
		return radarContacts.get(radarContacts.indexOf(contact));
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#getContact(java.lang.String)
	 */
	public RadarContact getContact(String id) {
		for (RadarContact currentContact: radarContacts) {
			if (currentContact.getContactID().equals(id))
				return currentContact;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#getContactCount()
	 */
	public int getContactCount() {
		
		return radarContacts.size();
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#removeContact(java.lang.String)
	 */
	public RadarContact removeContact(String id) {
		RadarContact currentContact = getContact(id);
		radarContacts.remove(currentContact);
		return currentContact;
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#returnContacts()
	 */
	public List<RadarContact> returnContacts() {
		List<RadarContact> contacts = new LinkedList<RadarContact>(radarContacts);
		return contacts;
	}

	/* (non-Javadoc)
	 * @see sef.module8.activity.Radar#returnContacts(java.util.Comparator)
	 */
	public List<RadarContact> returnContacts(Comparator<RadarContact> comparator) {
		List<RadarContact> sortList = returnContacts();
		sortList.sort(comparator);
		return sortList;
//		return Collections.sort(arg0, new DistanceComparator());
	}

	
}
