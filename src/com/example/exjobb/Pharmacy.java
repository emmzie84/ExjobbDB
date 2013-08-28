package com.example.exjobb;

import java.util.Calendar;

public class Pharmacy {
	public String id;
	public String chName;
	public String phName;
	public String addr;
	public String pCode;
	public String pArea;
	public String wPage;
	public String pNbr;
	public String opHWD;
	public String clHWD;
	public String opHSAT;
	public String clHSAT;
	public String opHSUN;
	public String clHSUN;
	public String lat;
	public String lon;
	public float distToPh;
	public int iconId;
	public String nbrOfDrug;
	
	public Pharmacy(String rID, String chainName, String pharmacyName, String address, String postalCode, String postalArea, String webPage, String phoneNbr, String openingHoursWD, String closingHoursWD, String openingHoursSAT, String closingHoursSAT, String openingHoursSUN, String closingHoursSUN, String latitude, String longitude) {
		super();
		this.id = rID;
		this.chName = chainName;
		this.phName = pharmacyName;
		this.addr = address;
		this.pCode = postalCode;
		this.pArea = postalArea;
		this.wPage = webPage;
		this.pNbr = phoneNbr;
		this.opHWD = openingHoursWD;
		this.clHWD = closingHoursWD;
		this.opHSAT = openingHoursSAT;
		this.clHSAT = closingHoursSAT;
		this.opHSUN = openingHoursSUN;
		this.clHSUN = closingHoursSUN;
		this.lat = latitude;
		this.lon = longitude;
		nbrOfDrug = null;
	}
	
	public Pharmacy(String rID, String chainName, String pharmacyName, String address, String postalCode, String postalArea, String webPage, String phoneNbr, String openingHoursWD, String closingHoursWD, String openingHoursSAT, String closingHoursSAT, String openingHoursSUN, String closingHoursSUN, String latitude, String longitude, float distToPharmacy) {
		super();
		this.id = rID;
		this.chName = chainName;
		this.phName = pharmacyName;
		this.addr = address;
		this.pCode = postalCode;
		this.pArea = postalArea;
		this.wPage = webPage;
		this.pNbr = phoneNbr;
		this.opHWD = openingHoursWD;
		this.clHWD = closingHoursWD;
		this.opHSAT = openingHoursSAT;
		this.clHSAT = closingHoursSAT;
		this.opHSUN = openingHoursSUN;
		this.clHSUN = closingHoursSUN;
		this.lat = latitude;
		this.lon = longitude;
		this.distToPh = distToPharmacy;
		nbrOfDrug = null;
	}
	
	public Pharmacy(String rID, String chainName, String pharmacyName, String address, String postalCode, String postalArea, String webPage, String phoneNbr, String openingHoursWD, String closingHoursWD, String openingHoursSAT, String closingHoursSAT, String openingHoursSUN, String closingHoursSUN, String latitude, String longitude, float distToPharmacy, String nbr) {
		super();
		this.id = rID;
		this.chName = chainName;
		this.phName = pharmacyName;
		this.addr = address;
		this.pCode = postalCode;
		this.pArea = postalArea;
		this.wPage = webPage;
		this.pNbr = phoneNbr;
		this.opHWD = openingHoursWD;
		this.clHWD = closingHoursWD;
		this.opHSAT = openingHoursSAT;
		this.clHSAT = closingHoursSAT;
		this.opHSUN = openingHoursSUN;
		this.clHSUN = closingHoursSUN;
		this.lat = latitude;
		this.lon = longitude;
		this.distToPh = distToPharmacy;
		this.nbrOfDrug = nbr;
	}
	
	public Pharmacy(String rID, String nbr) {
		super();
		this.id = rID;
		this.nbrOfDrug = nbr;
	}
	
	public String getNbrOfDrug() {
		if(nbrOfDrug == null) {
			return "";
		}
		else if(Integer.parseInt(nbrOfDrug) > 1) {
			return nbrOfDrug + " st tillg�ngliga";
		}
		else {
			return nbrOfDrug + " st tillg�nglig";
		}
	}
	
	public String getDistance() {
		StringBuffer sb = new StringBuffer();
		int iDist = Math.round(distToPh);
		double dDist;
		if (iDist < 1000) {
			dDist = ((double) iDist)/(10);
			iDist = (int) Math.round(dDist);
			int retDist = iDist*10;
			sb.append(retDist);
			sb.append(" m");
		}
		else {
			dDist = ((double) iDist)/(100);
			iDist = (int) Math.round(dDist);
			double retDist = ((double) iDist)/(10);
			sb.append(retDist);
			sb.append(" km");
		}
		return sb.toString();
	}
	
	public void setDistance(float dist) {
		distToPh = dist;
	}
	
	public String getOpeningHoursToday() {
		Calendar cal = Calendar.getInstance();
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		
		if(dayOfWeek == 1) {//Sunday
			return getOpeningHoursSUN();
		}
		else if(dayOfWeek == 7) {//Saturday
			return getOpeningHoursSAT();
		}
		else {//Weekday
			return getOpeningHoursWD();
		}
	}
	
	public String getOpeningHoursWD() {
		StringBuffer sb = new StringBuffer();
		if(opHWD.equals("Closed") || clHWD.equals("Closed")) {
			sb.append("St�ngt");
		}
		else {
			sb.append(opHWD);
			sb.append("-");
			sb.append(clHWD);
		}
		
		return sb.toString();
	}
	
	public String getOpeningHoursSAT() {
		StringBuffer sb = new StringBuffer();
		if(opHSAT.equals("Closed") || clHSAT.equals("Closed")) {
			sb.append("St�ngt");
		}
		else {
			sb.append(opHSAT);
			sb.append("-");
			sb.append(clHSAT);
		}
		
		return sb.toString();
	}
	
	public String getOpeningHoursSUN() {
		StringBuffer sb = new StringBuffer();
		if(opHSUN.equals("Closed") && clHSUN.equals("Closed")) {
			sb.append("St�ngt");
		}
		else {
			sb.append(opHSUN);
			sb.append("-");
			sb.append(clHSUN);
		}
		
		return sb.toString();
	}
	
	public String getPharmacyName() {
		return phName;
	}
	
	public void setIcon() {
		if(chName.equals("Apoteket")) {
			iconId = R.drawable.apoteket_ikon;
		}
		else if(chName.equals("Apotek Hj�rtat")) {
			iconId = R.drawable.apotekhjartat_ikon;
		}
		else if(chName.equals("Apoteksgruppen")) {
			iconId = R.drawable.apoteksg_ikon;
		}
		else if(chName.equals("Cura apoteket")) {
			iconId = R.drawable.curaa_ikon;
		}
		else if(chName.equals("Lloyds Apotek")) {
			iconId = R.drawable.lloydsa_ikon;
		}
		else if(chName.equals("Kronans droghandel")) {
			iconId = R.drawable.kronansd_ikon;
		}
		else if(chName.equals("Medstop")) {
			iconId = R.drawable.medstop_ikon;
		}
		else if(chName.equals("V�rdapoteket")) {
			iconId = R.drawable.varda_ikon;
		}
		else {
			iconId = R.drawable.apotek_ikon;
		}
	}
	
	public int getIcon() {
		return iconId;
	}
	
	public String getWebPage() {
		return wPage;
	}
	
	public String getPhoneNbr() {
		return pNbr;
	}
	
	public String getAddress() {
		return addr;
	}
	
	public String getPostalAC() {
		StringBuffer sb = new StringBuffer();
		sb.append(pCode);
		sb.append(" ");
		sb.append(pArea.toUpperCase());
		return sb.toString();
	}
}
