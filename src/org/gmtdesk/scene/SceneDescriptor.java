package org.gmtdesk.scene;

public enum SceneDescriptor {
    MAIN ("main.fxml", "Главный экран");

    private final String sceneName;
    private final String title;
    private final String scenePath;

    SceneDescriptor(String name, String title) {
        this.sceneName = name;
        this.title = title;
        this.scenePath = "/fxml/" + sceneName;
    }

    public String getSceneName() {
        return sceneName;
    }

    public String getTitle() {
        return title;
    }

    public String getScenePath() {
        return scenePath;
    }
}
