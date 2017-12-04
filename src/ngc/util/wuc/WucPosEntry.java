package ngc.util.wuc;

public class WucPosEntry {
   private final String wuc;
   private final String pos;
   
   public WucPosEntry(String wuc, String pos){
      this.wuc = wuc;
      this.pos = pos;
   }

   public String getWuc(){
      return wuc;
   }
   
   public String getPosition(){
      return pos;
   }
   
   public boolean equals(WucPosEntry entryToBeCompared){
      if(!this.pos.equals(entryToBeCompared.getPosition())){
         return false;
      } else if (!this.wuc.equals(entryToBeCompared.getWuc())){
         return false;
      }
      return true;
   }

}
