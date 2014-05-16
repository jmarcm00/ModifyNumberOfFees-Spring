/* Application developed for AW subject, belonging to passive operations
 group.*/

package es.unileon.ulebank.utils;

/**
 *
 * @author gonzalo
 */
public class CifControl {
    
    private char[] cifControlLetters;
    private static CifControl instance=null;
    
    private CifControl(){
        char[] data = {'J','A','B','C','D','E','F','G','H','I'};
        cifControlLetters = data;
    }
    
    public static CifControl instance(){
        if(instance == null){
            instance = new CifControl();
        }
        return instance;
    }
    
    public boolean isCifValid(char entityLetter, int provinceCode, int registrationCode, char controlCode){
        boolean result=false;
        entityLetter = Character.toUpperCase(entityLetter);
        if(entityLetter >='A' && entityLetter <='Z' && provinceCode<100 && provinceCode>0 && Integer.toString(registrationCode).length()<=5){
            int code = provinceCode*100000 + registrationCode;
            int controlObtained = getControl(code);
            if (Character.isLetter(controlCode)){
                if(cifControlLetters[controlObtained]==controlCode){
                    result = true;
                }
            }else if(controlObtained==Integer.parseInt(Character.toString(controlCode))){
               result = true;
            }
        }
        return result;
    }
    
    private static int getControl(int code){
        int result =0;
        String strCode= Integer.toString(code);
        for(int i=0; i<strCode.length(); i++){
            if(((i+1)/2)*2==i+1){
                result+=2*Integer.parseInt(Character.toString(strCode.charAt(i)));
            }else{
                result+=Integer.parseInt(Character.toString(strCode.charAt(i)));
            }
        }
        String controlData = String.valueOf(result);
        result = Integer.parseInt(Character.toString(controlData.charAt(controlData.length()-1)));
        if(result != 0)
            result = 10-result;
        return result;
    }
}
