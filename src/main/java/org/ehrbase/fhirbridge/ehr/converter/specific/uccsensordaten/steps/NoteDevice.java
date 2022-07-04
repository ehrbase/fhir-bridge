package org.ehrbase.fhirbridge.ehr.converter.specific.uccsensordaten.steps;

public class NoteDevice {

    String type;
    String device_id;
    String manufacturer;
    String deviceName;
    String version;

    public NoteDevice() {
    }

    public NoteDevice(String type, String device_id, String manufacturer, String deviceName, String version) {
        this.type = type;
        this.device_id = device_id;
        this.manufacturer = manufacturer;
        this.deviceName = deviceName;
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Manufacturer: " + getManufacturer() + ", device name: " + getDeviceName() + ", version: " + getVersion();
    }
}
