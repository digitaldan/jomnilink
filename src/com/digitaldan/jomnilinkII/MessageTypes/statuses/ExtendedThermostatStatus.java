package com.digitaldan.jomnilinkII.MessageTypes.statuses;

public class ExtendedThermostatStatus extends ThermostatStatus {

	private int humidity;
	private int humiditySetpoint; 
	private int dehumiditySetpoint;
	private int outdoorTemp; 
	private int extendedStatus;
	
	public ExtendedThermostatStatus(int number, int status, int temp,
			int heatSetpotint, int coolSetpoint, int systemMode, boolean fan,
			boolean hold, int humidity, int humiditySetpoint, 
			int dehumiditySetpoint, int outdoorTemp, int extendedStatus ) {
		super(number, status, temp, heatSetpotint, coolSetpoint, systemMode,
				fan, hold);
		this.humidity = humidity;
		this.humiditySetpoint = humiditySetpoint;
		this.dehumiditySetpoint = dehumiditySetpoint;
		this.outdoorTemp = outdoorTemp;
		this.extendedStatus = extendedStatus;
		
	}
	
	public int getHumidity() {
		return humidity;
	}
	public int getHumiditySetpoint() {
		return humiditySetpoint;
	}
	public int getDehumiditySetpoint() {
		return dehumiditySetpoint;
	}
	public int getOutdoorTemp() {
		return outdoorTemp;
	}
	public int getExtendedStatus() {
		return extendedStatus;
	}

	public void setHumidity(int humidity) {
      this.humidity = humidity;
   }

   public void setHumiditySetpoint(int humiditySetpoint) {
      this.humiditySetpoint = humiditySetpoint;
   }

   public void setDehumiditySetpoint(int dehumiditySetpoint) {
      this.dehumiditySetpoint = dehumiditySetpoint;
   }

   public void setOutdoorTemp(int outdoorTemp) {
      this.outdoorTemp = outdoorTemp;
   }

   public void setExtendedStatus(int extendedStatus) {
      this.extendedStatus = extendedStatus;
   }

   public String toString() {
	    final String TAB = "    ";
	    String retValue = "";
	    
	    retValue = "ThermostatStatus ( "
	    	+ "number = " + this.number + TAB
	        + "status = " + this.getStatus() + TAB
	        + "temperature = " + this.getTemperature() + TAB
	        + "heatSetpotint = " + this.getHeatSetpotint() + TAB
	        + "coolSetpoint = " + this.getCoolSetpoint() + TAB
	        + "systemMode = " + this.getMode() + TAB
	        + "fanMode = " + this.isFan() + TAB
	        + "holdStatus = " + this.isHold() + TAB
	        + "humidity = " + this.humidity + TAB
	        + "humiditySetpoint = " + this.humiditySetpoint + TAB
	        + "dehumiditySetpoint = " + this.dehumiditySetpoint + TAB
	        + "outdoorTemp = " + this.outdoorTemp + TAB
	        + "extendedStatus = " + this.extendedStatus + TAB
	        + " )";
	
	    return retValue;
	}

}
