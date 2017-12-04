package ngc.util.wuc;

public final class PubFileNameUtils {
   private final static String IPD_FILE_DESIGNATOR = "941";
   private final static String INSTALL_PROCEDURE_DESIGNATOR = "520";
   private final static String REMOVE_PROCEDURE_DESIGNATOR = "720";

   public static String replaceIpdDesignatorWithInstallDesignator(String ipdFileName) {
      return ipdFileName.replace(IPD_FILE_DESIGNATOR, REMOVE_PROCEDURE_DESIGNATOR);
   }

   public static String replaceIpdDesignatorWithRemoveDesignator(String ipdFileName) {
      return ipdFileName.replace(IPD_FILE_DESIGNATOR, INSTALL_PROCEDURE_DESIGNATOR);
   }

   public static String removeVersionAndExtension(String fileName) {
      if(fileName.contains("_")){
         fileName = fileName.substring(0, fileName.indexOf("_"));
      }
      return fileName;
   }

   public static String replaceInstallOrRemoveDesignatorWithIpdDesignator(String fileName) {
      if(fileName.contains(INSTALL_PROCEDURE_DESIGNATOR)){
         return fileName.replace(INSTALL_PROCEDURE_DESIGNATOR, IPD_FILE_DESIGNATOR);
      } else if (fileName.contains(REMOVE_PROCEDURE_DESIGNATOR)){
         return fileName.replace(REMOVE_PROCEDURE_DESIGNATOR, IPD_FILE_DESIGNATOR);
      } else {
         return fileName;
      }
   }


}
