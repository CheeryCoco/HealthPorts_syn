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
	
	public static JSONObject getPatientName(JSONObject JSONCCD){
		try {
			return JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("recordTarget").getJSONObject("patientRole").getJSONObject("patient").getJSONObject("name");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSONCCD;
	}
	
	public static JSONObject getPatientID(JSONObject JSONCCD){
		try {
			return JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("recordTarget").getJSONObject("patientRole").getJSONObject("id");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSONCCD;
	}
	
	public static Object getPatientGender(JSONObject JSONCCD) throws JSONException{
		return JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("recordTarget").getJSONObject("patientRole").getJSONObject("patient").getJSONObject("administrativeGenderCode").get("displayName");
	}
	
	public static Object getPatientAddr(JSONObject JSONCCD) throws JSONException{
		return JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("recordTarget").getJSONObject("patientRole").getJSONObject("addr").get("streetAddressLine");
	}
	
	public static Object getPatientBirthDate(JSONObject JSONCCD) throws JSONException{
		/*try {
			
			return JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("recordTarget").getJSONObject("patientRole").getJSONObject("patient").getJSONObject("birthTime").getString("value");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("recordTarget").getJSONObject("patientRole").getJSONObject("patient").getJSONObject("birthTime").get("value");
	}
	
	public static Object getPatientBirthYear(JSONObject JSONCCD) throws JSONException{
		return (String.valueOf(JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("recordTarget").getJSONObject("patientRole").getJSONObject("patient").getJSONObject("birthTime").get("value"))).substring(0, 4);
	}
	
	public static Object getPatientBirthMonth(JSONObject JSONCCD) throws JSONException{
		return (String.valueOf(JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("recordTarget").getJSONObject("patientRole").getJSONObject("patient").getJSONObject("birthTime").get("value"))).substring(4, 6);
	}
	
	public static Object getPatientBirthDay(JSONObject JSONCCD) throws JSONException{
		return (String.valueOf(JSONCCD.getJSONObject("ClinicalDocument").getJSONObject("recordTarget").getJSONObject("patientRole").getJSONObject("patient").getJSONObject("birthTime").get("value"))).substring(6);
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
