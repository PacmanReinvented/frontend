package client;

import entities.User;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class REST implements IRestTemplate
{
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public User loginUser(String username, String password)
    {
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("pass", password);

        User result = restTemplate.getForObject("http://localhost:8096/user/{username}/{pass}", User.class, params);
        return result;
    }
    @Override
    public boolean registerUser(User user)
    {
        boolean state;
        final String query = "http://localhost:8096/user/";
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.postForObject(query, user, User.class);
            state = true;
        } catch (Exception e) {
            state = false;
        }
        return state;
    }
}
