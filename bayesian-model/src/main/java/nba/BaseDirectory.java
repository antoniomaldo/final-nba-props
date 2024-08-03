package nba;

public enum BaseDirectory {
    BASE_DIR_ANTONIO("C:\\models\\nba-player-props\\");

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
