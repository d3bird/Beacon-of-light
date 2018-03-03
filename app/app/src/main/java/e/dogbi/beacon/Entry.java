package e.dogbi.beacon;

public class Entry{
    string ID;
    string GPS;
    int status;

    public Entry(string ID, string GPS, int status) {
        this.ID = ID;
        this.GPS = GPS;
        this.status = status;
    }

    public Entry() {
        this.ID = "NO ID";
        this.GPS = "NO GPS";
        this.status = "-1";
    }

    public string getID() {
        return ID;
    }

    public string getGPS() {
        return GPS;
    }

    public int getStatus() {
        return status;
    }

    public void setID(string ID) {
        this.ID = ID;
    }

    public void setGPS(string GPS) {
        this.GPS = GPS;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}