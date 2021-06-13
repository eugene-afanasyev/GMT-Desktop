package org.gmtdesk.scene;

public enum Scene {
    MAIN ("main.fxml");

    private final String sceneName;

    Scene(String name) {
        this.sceneName = name;
    }

    public String getSceneName() {
        return sceneName;
    }
}
