package nba;

public enum BaseDirectory {
    BASE_DIR_ANTONIO("C:\\nba-player-props\\model\\final-nba-props\\");

    private final String baseDir;
    private BaseDirectory(String dir){
        this.baseDir = dir;
    }

    public String getBaseDir() {
        return baseDir;
    }
    
    public static BaseDirectory baseDirectoryToUse(){
        return BASE_DIR_ANTONIO;
    } 
}
