package edu.gatech.i3l.HealthPort.ccdparse;

//import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SyntheticEHR {
	
	public static JSONObject getCCDObject(JSONObject JSONCCD, String CCDObj){
		try {
			return JSONCCD.getJSONObject(CCDObj);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSONCCD;
	}
	
	public static String getPatientName(JSONObject JSONCCD){
		try {
			return String.valueOf((JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("recordTarget").getJSONObject("patientRole").getJSONObject("patient").getJSONObject("name").getJSONArray("given")).getString(0)) + " " + String.valueOf((JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("recordTarget").getJSONObject("patientRole").getJSONObject("patient").getJSONObject("name").getJSONArray("given")).getString(1)) + " " + String.valueOf(JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("recordTarget").getJSONObject("patientRole").getJSONObject("patient").getJSONObject("name").get("family"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "N/A";
	}
	
	public static int getPatientID(JSONObject JSONCCD) throws JSONException{
			JSONObject ID = JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("recordTarget").getJSONObject("patientRole").getJSONObject("id");
			String StrID = (String)ID.get("root") + (String)ID.get("extension");
			return Math.abs(StrID.hashCode());
	}
	
	public static int getPatientGenderID(JSONObject JSONCCD) throws JSONException{
		return Math.abs(((String)JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("recordTarget").getJSONObject("patientRole").getJSONObject("patient").getJSONObject("administrativeGenderCode").get("code")).hashCode());
	}
	
	public static Object getPatientAddr(JSONObject JSONCCD) throws JSONException{
		return JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("recordTarget").getJSONObject("patientRole").getJSONObject("addr").get("streetAddressLine");
	}
	
	public static String getBirthDate(JSONObject JSONCCD) throws JSONException{
		/*try {
			
			return JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("recordTarget").getJSONObject("patientRole").getJSONObject("patient").getJSONObject("birthTime").getString("value");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return (String) JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("recordTarget").getJSONObject("patientRole").getJSONObject("patient").getJSONObject("birthTime").get("value");
	}
	
	public static int getBirthYear(JSONObject JSONCCD) throws JSONException{
		return Integer.parseInt((String.valueOf(JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("recordTarget").getJSONObject("patientRole").getJSONObject("patient").getJSONObject("birthTime").get("value"))).substring(0, 4));
	}
	
	public static int getBirthMonth(JSONObject JSONCCD) throws JSONException{
		return Integer.parseInt((String.valueOf(JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("recordTarget").getJSONObject("patientRole").getJSONObject("patient").getJSONObject("birthTime").get("value"))).substring(4, 6));
	}
	
	public static int getBirthDay(JSONObject JSONCCD) throws JSONException{
		return Integer.parseInt((String.valueOf(JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("recordTarget").getJSONObject("patientRole").getJSONObject("patient").getJSONObject("birthTime").get("value"))).substring(6));
	}
	
	public static JSONObject getPatientRace(JSONObject JSONCCD) throws JSONException{
		return JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("recordTarget").getJSONObject("patientRole").getJSONObject("patient").getJSONObject("raceCode");
	}
	
	public static JSONObject getPatientMaritalStatus(JSONObject JSONCCD) throws JSONException{
		return JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("recordTarget").getJSONObject("patientRole").getJSONObject("patient").getJSONObject("maritalStatusCode");
	}
	
	public static Object getPatientPhone(JSONObject JSONCCD) throws JSONException{
		return JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("recordTarget").getJSONObject("patientRole").getJSONObject("telecom").get("value");
	}
	
	public static Object getCareSiteName(JSONObject JSONCCD) throws JSONException{
		return JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("author").getJSONObject("assignedAuthor").getJSONObject("representedOrganization").get("name");
	}
	
	public static Object getCareSiteAddr(JSONObject JSONCCD) throws JSONException{
		return JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("author").getJSONObject("assignedAuthor").getJSONObject("representedOrganization").getJSONObject("addr").get("streetAddressLine");
	}
	
	public static Object getCareSiteCity(JSONObject JSONCCD) throws JSONException{
		return JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("author").getJSONObject("assignedAuthor").getJSONObject("representedOrganization").getJSONObject("addr").get("city");
	}
	
	public static Object getCareSiteState(JSONObject JSONCCD) throws JSONException{
		return JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("author").getJSONObject("assignedAuthor").getJSONObject("representedOrganization").getJSONObject("addr").get("state");
	}
	
	public static Object getCareSiteZip(JSONObject JSONCCD) throws JSONException{
		return JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("author").getJSONObject("assignedAuthor").getJSONObject("representedOrganization").getJSONObject("addr").get("postalCode");
	}
	
	public static Object getCareSitePhone(JSONObject JSONCCD) throws JSONException{
		return ((String)JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("author").getJSONObject("assignedAuthor").getJSONObject("representedOrganization").getJSONObject("telecom").get("value")).substring(4);
	}
	
	public static Object getProviderName(JSONObject JSONCCD) throws JSONException{
		return JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("author").getJSONObject("assignedAuthor").getJSONObject("assignedPerson").get("name");
	}
	
	public static JSONObject getProblems(JSONObject JSONCCD) throws JSONException{
		JSONObject tempJSONObj = JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("component").getJSONObject("structuredBody");
		JSONArray temp = tempJSONObj.getJSONArray("component");
		for (int i = 0; i < temp.length(); i++){
			if (temp.getJSONObject(i).getJSONObject("section").get("title").equals("Problems")){
				return temp.getJSONObject(i).getJSONObject("section").getJSONObject("entry").getJSONObject("act").getJSONObject("entryRelationship").getJSONObject("observation").getJSONObject("value");
			}	
		}
		return tempJSONObj;
	}
	
	public static String getCondIDold(JSONObject JSONCCD) throws JSONException{
		JSONObject tempJSONObj = JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("component").getJSONObject("structuredBody");
		JSONArray temp = tempJSONObj.getJSONArray("component");
		for (int i = 0; i < temp.length(); i++){
			if (temp.getJSONObject(i).getJSONObject("section").get("title").equals("Problems")){
				return String.valueOf(temp.getJSONObject(i).getJSONObject("section").getJSONObject("entry").getJSONObject("act").getJSONObject("entryRelationship").getJSONObject("observation").getJSONObject("value").getJSONObject("translation").get("code"));
			}	
		}
		return "N/A";
	}
	
	public static String[] getCond(JSONObject JSONCCD) throws JSONException{
		JSONObject tempJSONObj = JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("component").getJSONObject("structuredBody");
		JSONArray temp = tempJSONObj.getJSONArray("component");
		for (int i = 0; i < temp.length(); i++){
			if (temp.getJSONObject(i).getJSONObject("section").get("title").equals("Problems")){
				String[] Cond = new String[50];
				try{
					JSONArray temp2 = temp.getJSONObject(i).getJSONObject("section").getJSONArray("entry");
					//System.out.println(temp2.length());
					for (int j = 0; j < temp2.length(); j++){
						//System.out.println(temp2.getJSONObject(j).getJSONObject("act").getJSONObject("entryRelationship").getJSONObject("observation").getJSONObject("value").getJSONObject("translation").get("displayName"));
						Cond[j] = String.valueOf(temp2.getJSONObject(j).getJSONObject("act").getJSONObject("entryRelationship").getJSONObject("observation").getJSONObject("value").getJSONObject("translation").get("code")) + "|" + temp2.getJSONObject(j).getJSONObject("act").getJSONObject("entryRelationship").getJSONObject("observation").getJSONObject("value").getJSONObject("translation").get("displayName") + "|" + temp2.getJSONObject(j).getJSONObject("act").getJSONObject("effectiveTime").getJSONObject("low").get("value");
					}
					return Cond;
				}
				catch (JSONException e){
					Cond[0] = String.valueOf(temp.getJSONObject(i).getJSONObject("section").getJSONObject("entry").getJSONObject("act").getJSONObject("entryRelationship").getJSONObject("observation").getJSONObject("value").getJSONObject("translation").get("code")) + "|" + String.valueOf(temp.getJSONObject(i).getJSONObject("section").getJSONObject("entry").getJSONObject("act").getJSONObject("entryRelationship").getJSONObject("observation").getJSONObject("value").getJSONObject("translation").get("displayName")) + "|" + String.valueOf(temp.getJSONObject(i).getJSONObject("section").getJSONObject("entry").getJSONObject("act").getJSONObject("effectiveTime").getJSONObject("low").get("value"));
					return Cond;
				}
				
			}	
		}
		String[] NA = new String[1];
		NA[0] = "N/A";
		return NA;
	}
	
	public static String[] getMeds(JSONObject JSONCCD) throws JSONException{
		JSONObject tempJSONObj = JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("component").getJSONObject("structuredBody");
		JSONArray temp = tempJSONObj.getJSONArray("component");
		for (int i = 0; i < temp.length(); i++){
			if (temp.getJSONObject(i).getJSONObject("section").get("title").equals("Medications")){
				String[] Med = new String[50];
				//String ref = null;
				try{
					JSONArray temp2 = temp.getJSONObject(i).getJSONObject("section").getJSONArray("entry");
					//System.out.println(temp2.length());
					for (int j = 0; j < temp2.length(); j++){
						//System.out.println(temp2.getJSONObject(j).getJSONObject("act").getJSONObject("entryRelationship").getJSONObject("observation").getJSONObject("value").getJSONObject("translation").get("displayName"));
						Med[j] = String.valueOf(temp2.getJSONObject(j).getJSONObject("substanceAdministration").getJSONObject("consumable").getJSONObject("manufacturedProduct").getJSONObject("manufacturedMaterial").getJSONObject("code").get("code")) + "|" + String.valueOf(temp2.getJSONObject(j).getJSONObject("substanceAdministration").getJSONObject("consumable").getJSONObject("manufacturedProduct").getJSONObject("manufacturedMaterial").getJSONObject("code").get("displayName")) + "|" + String.valueOf(temp2.getJSONObject(j).getJSONObject("substanceAdministration").getJSONObject("statusCode").get("code")) + "|" + String.valueOf(temp2.getJSONObject(j).getJSONObject("substanceAdministration").getJSONObject("effectiveTime").getJSONObject("low").get("value")) + "|" + String.valueOf(temp2.getJSONObject(j).getJSONObject("substanceAdministration").getJSONObject("doseQuantity").get("value")) + "|" + String.valueOf(temp2.getJSONObject(j).getJSONObject("substanceAdministration").getJSONObject("doseQuantity").get("unit"));
						//ref = String.valueOf(temp2.getJSONObject(j).getJSONObject("substanceAdministration").getJSONObject("consumable").getJSONObject("manufacturedProduct").getJSONObject("manufacturedMaterial").getJSONObject("code").getJSONObject("originalText").getJSONObject("reference").get("value")).substring(1);
					}
					return Med;
				}
				catch (JSONException e){
					Med[0] = String.valueOf(temp.getJSONObject(i).getJSONObject("substanceAdministration").getJSONObject("consumable").getJSONObject("manufacturedProduct").getJSONObject("manufacturedMaterial").getJSONObject("code").get("code")) + "|" + String.valueOf(temp.getJSONObject(i).getJSONObject("substanceAdministration").getJSONObject("consumable").getJSONObject("manufacturedProduct").getJSONObject("manufacturedMaterial").getJSONObject("code").get("displayName")) + "|" + String.valueOf(temp.getJSONObject(i).getJSONObject("substanceAdministration").getJSONObject("statusCode").get("code")) + "|" + String.valueOf(temp.getJSONObject(i).getJSONObject("substanceAdministration").getJSONObject("effectiveTime").getJSONObject("low").get("value")) + "|" + String.valueOf(temp.getJSONObject(i).getJSONObject("substanceAdministration").getJSONObject("doseQuantity").get("value")) + "|" + String.valueOf(temp.getJSONObject(i).getJSONObject("substanceAdministration").getJSONObject("doseQuantity").get("unit"));
					//ref = String.valueOf(temp.getJSONObject(i).getJSONObject("substanceAdministration").getJSONObject("consumable").getJSONObject("manufacturedProduct").getJSONObject("manufacturedMaterial").getJSONObject("code").getJSONObject("originalText").getJSONObject("reference").get("value")).substring(1);
					return Med;
				}
				//System.out.println(ref);
			}	
		}
		String[] NA = new String[1];
		NA[0] = "N/A";
		return NA;
	}
	
	public static String[] getObs(JSONObject JSONCCD) throws JSONException{
		JSONObject tempJSONObj = JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("component").getJSONObject("structuredBody");
		JSONArray temp = tempJSONObj.getJSONArray("component");
		for (int i = 0; i < temp.length(); i++){
			if (temp.getJSONObject(i).getJSONObject("section").get("title").equals("Vital Signs")){
				String[] Obs = new String[50];
				try{
					JSONArray temp2 = temp.getJSONObject(i).getJSONObject("section").getJSONObject("text").getJSONObject("table").getJSONObject("tbody").getJSONArray("tr");
					for (int j = 0; j < temp2.length(); j++){
						JSONArray temp3 = temp2.getJSONObject(j).getJSONArray("td");
						Obs[j] = String.valueOf(temp3.get(0)) + "|" + String.valueOf(temp3.getJSONObject(1).get("content")) + "|" + String.valueOf(temp3.getJSONObject(2).get("content")) + "|" + String.valueOf(temp3.getJSONObject(3).get("content")) + "|" + String.valueOf(temp3.getJSONObject(4).get("content")) + "|" + String.valueOf(temp3.getJSONObject(5).get("content")) + "|" + String.valueOf(temp3.getJSONObject(6).get("content")) + "|" + String.valueOf(temp3.getJSONObject(7).get("content"));		
					}
					return Obs;
				}
				catch (JSONException e){
					JSONArray temp2 = temp.getJSONObject(i).getJSONObject("section").getJSONObject("text").getJSONObject("table").getJSONObject("tbody").getJSONObject("tr").getJSONArray("td");
					Obs[0] = String.valueOf(temp2.get(0)) + "|" + String.valueOf(temp2.getJSONObject(1).get("content")) + "|" + String.valueOf(temp2.getJSONObject(2).get("content")) + "|" + String.valueOf(temp2.getJSONObject(3).get("content")) + "|" + String.valueOf(temp2.getJSONObject(4).get("content")) + "|" + String.valueOf(temp2.getJSONObject(5).get("content")) + "|" + String.valueOf(temp2.getJSONObject(6).get("content")) + "|" + String.valueOf(temp2.getJSONObject(7).get("content"));
					return Obs;
				}
			}	
		}
		String[] NA = new String[1];
		NA[0] = "N/A";
		return NA;
	}
	
	public static String getCondSID(JSONObject JSONCCD) throws JSONException{
		JSONObject tempJSONObj = JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("component").getJSONObject("structuredBody");
		JSONArray temp = tempJSONObj.getJSONArray("component");
		for (int i = 0; i < temp.length(); i++){
			if (temp.getJSONObject(i).getJSONObject("section").get("title").equals("Problems")){
				return String.valueOf(temp.getJSONObject(i).getJSONObject("section").getJSONObject("entry").getJSONObject("act").getJSONObject("entryRelationship").getJSONObject("observation").getJSONObject("value").get("code"));
			}	
		}
		return "N/A";
	}
	
	public static int getCondStart(JSONObject JSONCCD) throws JSONException{
		JSONObject tempJSONObj = JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("component").getJSONObject("structuredBody");
		JSONArray temp = tempJSONObj.getJSONArray("component");
		for (int i = 0; i < temp.length(); i++){
			if (temp.getJSONObject(i).getJSONObject("section").get("title").equals("Problems")){
				return ((Number)temp.getJSONObject(i).getJSONObject("section").getJSONObject("entry").getJSONObject("act").getJSONObject("effectiveTime").getJSONObject("low").get("value")).intValue();
			}
		}
		return 0;
	}
	
	public static int getCondEnd(JSONObject JSONCCD) throws JSONException{
		JSONObject tempJSONObj = JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("component").getJSONObject("structuredBody");
		JSONArray temp = tempJSONObj.getJSONArray("component");
		for (int i = 0; i < temp.length(); i++){
			if (temp.getJSONObject(i).getJSONObject("section").get("title").equals("Problems")){
				try {
					return (int)temp.getJSONObject(i).getJSONObject("section").getJSONObject("entry").getJSONObject("act").getJSONObject("effectiveTime").getJSONObject("high").get("value");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					return 0;
				}
				//JSONArray temp2 = temp.getJSONObject(i).getJSONObject("section").getJSONArray("entry");
				//for (int j = 0; j < temp.length(); j++){
					
				//}
			}
		}
		return 0;
	}
	
	public static String getDrugID(JSONObject JSONCCD) throws JSONException{
		JSONObject tempJSONObj = JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("component").getJSONObject("structuredBody");
		JSONArray temp = tempJSONObj.getJSONArray("component");
		for (int i = 0; i < temp.length(); i++){
			if (temp.getJSONObject(i).getJSONObject("section").get("title").equals("Medications")){
				return String.valueOf(temp.getJSONObject(i).getJSONObject("section").getJSONObject("entry").getJSONObject("substanceAdministration").getJSONObject("consumable").getJSONObject("manufacturedProduct").getJSONObject("manufacturedMaterial").getJSONObject("code").getJSONObject("translation").get("code"));
			}	
		}
		return "N/A";
	}
	
	public static String getDrugName(JSONObject JSONCCD) throws JSONException{
		JSONObject tempJSONObj = JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("component").getJSONObject("structuredBody");
		JSONArray temp = tempJSONObj.getJSONArray("component");
		for (int i = 0; i < temp.length(); i++){
			if (temp.getJSONObject(i).getJSONObject("section").get("title").equals("Medications")){
				return String.valueOf(temp.getJSONObject(i).getJSONObject("section").getJSONObject("entry").getJSONObject("substanceAdministration").getJSONObject("consumable").getJSONObject("manufacturedProduct").getJSONObject("manufacturedMaterial").getJSONObject("code").getJSONObject("translation").get("displayName"));
			}	
		}
		return "N/A";
	}
	
	public static String getDrugDose(JSONObject JSONCCD) throws JSONException{
		JSONObject tempJSONObj = JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("component").getJSONObject("structuredBody");
		JSONArray temp = tempJSONObj.getJSONArray("component");
		for (int i = 0; i < temp.length(); i++){
			if (temp.getJSONObject(i).getJSONObject("section").get("title").equals("Medications")){
				return String.valueOf(temp.getJSONObject(i).getJSONObject("section").getJSONObject("entry").getJSONObject("substanceAdministration").getJSONObject("doseQuantity").get("value")) + " " + String.valueOf(temp.getJSONObject(i).getJSONObject("section").getJSONObject("entry").getJSONObject("substanceAdministration").getJSONObject("doseQuantity").get("unit"));
			}	
		}
		return "N/A";
	}
	
	public static String getDrugStart(JSONObject JSONCCD) throws JSONException{
		JSONObject tempJSONObj = JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("component").getJSONObject("structuredBody");
		JSONArray temp = tempJSONObj.getJSONArray("component");
		for (int i = 0; i < temp.length(); i++){
			if (temp.getJSONObject(i).getJSONObject("section").get("title").equals("Medications")){
				return String.valueOf(temp.getJSONObject(i).getJSONObject("section").getJSONObject("entry").getJSONObject("substanceAdministration").getJSONObject("effectiveTime").getJSONObject("low").get("value"));
			}	
		}
		return "N/A";
	}
	
	public static String getDrugEnd(JSONObject JSONCCD) throws JSONException{
		JSONObject tempJSONObj = JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("component").getJSONObject("structuredBody");
		JSONArray temp = tempJSONObj.getJSONArray("component");
		for (int i = 0; i < temp.length(); i++){
			if (temp.getJSONObject(i).getJSONObject("section").get("title").equals("Medications")){
				return String.valueOf(temp.getJSONObject(i).getJSONObject("section").getJSONObject("entry").getJSONObject("substanceAdministration").getJSONObject("effectiveTime").getJSONObject("high").get("nullFlavor"));
			}	
		}
		return "N/A";
	}
	
	public static Object getResults(JSONObject JSONCCD) throws JSONException{
		JSONObject tempJSONObj = JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("component").getJSONObject("structuredBody");
		JSONArray temp = tempJSONObj.getJSONArray("component");
		for (int i = 0; i < temp.length(); i++){
			if (temp.getJSONObject(i).getJSONObject("section").get("title").equals("Results")){
				return temp.getJSONObject(i).getJSONObject("section").getJSONObject("text").getJSONObject("table").getJSONObject("tbody").get("tr");
				//JSONArray temp2 = temp.getJSONObject(i).getJSONObject("section").getJSONObject("text").getJSONObject("table").getJSONObject("tbody").getJSONArray("tr");
				//for (int j = 0; j < temp2.length(); j++){
					//System.out.println(temp2.getJSONObject(j).getJSONObject("td"));
					//return temp2.getJSONObject(j).getJSONObject("th").get("content"));
					//if (temp2.getJSONObject(j).getJSONObject("th").get("content").equals("Body weight")){
						//return temp2.getJSONObject(j).get("td");
					//}
			}	
		}
		return null;
	}
	
	public static String getObsDate(JSONObject JSONCCD) throws JSONException{
		JSONObject tempJSONObj = JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("component").getJSONObject("structuredBody");
		JSONArray temp = tempJSONObj.getJSONArray("component");
		for (int i = 0; i < temp.length(); i++){
			if (temp.getJSONObject(i).getJSONObject("section").get("title").equals("Vital Signs")){
				try {
					return String.valueOf(temp.getJSONObject(i).getJSONObject("section").getJSONObject("text").getJSONObject("table").getJSONObject("thead").getJSONObject("tr").get("td"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					return "N/A";
				}//.getJSONObject("act").getJSONObject("entryRelationship").getJSONObject("observation").getJSONObject("value");
			
				//System.out.println(temp2);
				//for (int j = 0; j < temp2.length(); j++){
					//if (temp2.getJSONObject(j).getJSONObject("th").get("content").equals("Date / Time: ")){
						//return String.valueOf(temp2.getJSONObject(j).get("td"));
					//}
				//}
			}	
		}
		return "N/A";
	}
	
	public static Object getWeight(JSONObject JSONCCD) throws JSONException{
		JSONObject tempJSONObj = JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("component").getJSONObject("structuredBody");
		JSONArray temp = tempJSONObj.getJSONArray("component");
		for (int i = 0; i < temp.length(); i++){
			if (temp.getJSONObject(i).getJSONObject("section").get("title").equals("Vital Signs")){
				JSONArray temp2 = temp.getJSONObject(i).getJSONObject("section").getJSONObject("text").getJSONObject("table").getJSONObject("tbody").getJSONArray("tr");//.getJSONObject("act").getJSONObject("entryRelationship").getJSONObject("observation").getJSONObject("value");
				//System.out.println(temp2);
				for (int j = 0; j < temp2.length(); j++){
					//System.out.println(temp2.getJSONObject(j).getJSONObject("th").get("content"));
					if (temp2.getJSONObject(j).getJSONObject("th").get("content").equals("Body weight")){
						return temp2.getJSONObject(j).get("td");
					}
				}
			}	
		}
		return 0;
	}
	
	public static Object getHeight(JSONObject JSONCCD) throws JSONException{
		JSONObject tempJSONObj = JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("component").getJSONObject("structuredBody");
		JSONArray temp = tempJSONObj.getJSONArray("component");
		for (int i = 0; i < temp.length(); i++){
			if (temp.getJSONObject(i).getJSONObject("section").get("title").equals("Vital Signs")){
				JSONArray temp2 = temp.getJSONObject(i).getJSONObject("section").getJSONObject("text").getJSONObject("table").getJSONObject("tbody").getJSONArray("tr");
				for (int j = 0; j < temp2.length(); j++){
					if (temp2.getJSONObject(j).getJSONObject("th").get("content").equals("Body height")){
						return temp2.getJSONObject(j).get("td");
					}
				}
			}	
		}
		return 0;
	}
	
	public static Object getHeightnew(JSONObject JSONCCD) throws JSONException{
		JSONObject tempJSONObj = JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("component").getJSONObject("structuredBody");
		JSONArray temp = tempJSONObj.getJSONArray("component");
		for (int i = 0; i < temp.length(); i++){
			if (temp.getJSONObject(i).getJSONObject("section").get("title").equals("Vital Signs")){
				JSONArray temp2 = temp.getJSONObject(i).getJSONObject("section").getJSONObject("text").getJSONObject("table").getJSONObject("tbody").getJSONObject("tr").getJSONArray("td");
				for (int j = 0; j < temp2.length(); j++){
					if (temp2.getJSONObject(j).getJSONObject("content").get("ID").equals("vital_1")){
						return temp2.getJSONObject(j).get("td");
					}
				}
			}	
		}
		return 0;
	}
	
	public static Object getSysBP(JSONObject JSONCCD) throws JSONException{
		JSONObject tempJSONObj = JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("component").getJSONObject("structuredBody");
		JSONArray temp = tempJSONObj.getJSONArray("component");
		for (int i = 0; i < temp.length(); i++){
			if (temp.getJSONObject(i).getJSONObject("section").get("title").equals("Vital Signs")){
				JSONArray temp2 = temp.getJSONObject(i).getJSONObject("section").getJSONObject("text").getJSONObject("table").getJSONObject("tbody").getJSONArray("tr");
				for (int j = 0; j < temp2.length(); j++){
					if (temp2.getJSONObject(j).getJSONObject("th").get("content").equals("Systolic BP")){
						return temp2.getJSONObject(j).get("td");
					}
				}
			}	
		}
		return 0;
	}
	
	public static Object getDiaBP(JSONObject JSONCCD) throws JSONException{
		JSONObject tempJSONObj = JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("component").getJSONObject("structuredBody");
		JSONArray temp = tempJSONObj.getJSONArray("component");
		for (int i = 0; i < temp.length(); i++){
			if (temp.getJSONObject(i).getJSONObject("section").get("title").equals("Vital Signs")){
				JSONArray temp2 = temp.getJSONObject(i).getJSONObject("section").getJSONObject("text").getJSONObject("table").getJSONObject("tbody").getJSONArray("tr");
				for (int j = 0; j < temp2.length(); j++){
					if (temp2.getJSONObject(j).getJSONObject("th").get("content").equals("Diastolic BP")){
						return temp2.getJSONObject(j).get("td");
					}
				}
			}	
		}
		return 0;
	}
	
	public static Object getPulse(JSONObject JSONCCD) throws JSONException{
		JSONObject tempJSONObj = JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("component").getJSONObject("structuredBody");
		JSONArray temp = tempJSONObj.getJSONArray("component");
		for (int i = 0; i < temp.length(); i++){
			if (temp.getJSONObject(i).getJSONObject("section").get("title").equals("Vital Signs")){
				JSONArray temp2 = temp.getJSONObject(i).getJSONObject("section").getJSONObject("text").getJSONObject("table").getJSONObject("tbody").getJSONArray("tr");
				for (int j = 0; j < temp2.length(); j++){
					if (temp2.getJSONObject(j).getJSONObject("th").get("content").equals("Pulse")){
						return temp2.getJSONObject(j).get("td");
					}
				}
			}	
		}
		return 0;
	}
	
	public static Object getRespRate(JSONObject JSONCCD) throws JSONException{
		JSONObject tempJSONObj = JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("component").getJSONObject("structuredBody");
		JSONArray temp = tempJSONObj.getJSONArray("component");
		for (int i = 0; i < temp.length(); i++){
			if (temp.getJSONObject(i).getJSONObject("section").get("title").equals("Vital Signs")){
				JSONArray temp2 = temp.getJSONObject(i).getJSONObject("section").getJSONObject("text").getJSONObject("table").getJSONObject("tbody").getJSONArray("tr");
				for (int j = 0; j < temp2.length(); j++){
					if (temp2.getJSONObject(j).getJSONObject("th").get("content").equals("Respiration Rate")){
						return temp2.getJSONObject(j).get("td");
					}
				}
			}	
		}
		return 0;
	}
	
	public static void getCCDObject(JSONObject JSONCCD){
		String jsonPrettyPrintString = null;
		try {
			jsonPrettyPrintString = JSONCCD.toString(4);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jsonPrettyPrintString);
	}
	

}
