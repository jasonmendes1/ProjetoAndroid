package pt.ipleiria.estg.dei.app_projeto.listeners;

import org.json.JSONArray;

public interface LoginListener {
    void onRefreshLogin(JSONArray response);
    void onValidateLogin(String token, String username);
}
