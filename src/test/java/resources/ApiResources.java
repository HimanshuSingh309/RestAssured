package resources;

public enum ApiResources {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	
	private String resources;
	
	ApiResources(String resources){
		
		this.resources = resources;
	}
	
	public String getResources() {
		
		return resources;
	}

}
