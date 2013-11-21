
package cz.fi.muni.pa165.calorycounter.serviceapi.dto;

/**
 * This entity represents weight categories by intervals for all common human weights in lbs.
 *
 * @author Martin Pasko (smartly23)
 */
public enum WeightCategory {
    
    _0_   ("less than 130 lbs"),
    _130_ ("130-154 lbs"), 
    _155_ ("155-179lbs"), 
    _180_ ("180-204lbs"),
    _205_ ("205+ lbs");
    
    private String showedCategory;
    
    private WeightCategory(String showedCategory) {
        this.showedCategory = showedCategory;
    }

    public String getShowedCategory() {
        return showedCategory;
    }
}
