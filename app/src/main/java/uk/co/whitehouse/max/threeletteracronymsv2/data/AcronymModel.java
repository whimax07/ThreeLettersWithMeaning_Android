package uk.co.whitehouse.max.threeletteracronymsv2.data;

public class AcronymModel {

    private static final AcronymModel theInstance = new AcronymModel();
    private static final String fileName = "SavedAcronyms.jobj";

    private AcronymList acronymsList = new AcronymList();
    private boolean updated;

    private AcronymModel() {

    }

    public static AcronymModel getInstance() {
        return theInstance;
    }

    public AcronymList getAcronymsList() {
        return acronymsList;
    }

    public void setAcronymsList(AcronymList acronymsList) {
        this.acronymsList = acronymsList;
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }
}
