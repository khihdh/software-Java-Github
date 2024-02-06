/**
 *
 */
package authentification;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;

/**
 * @author jeremy Castex
 *
 */
public final class Authentification {

	static String autoconnect = "no";
	static String cle;
	static String username;
	static String password;
	static Boolean accept = false;
	static String bearertoken;

	public Authentification() {
		super();
		username = "";
		password = "";
	}

	public static boolean authentification() {
		URL url;
		// creation du json qu'on transmet � l'api
		String jsonStringauth = "grant_type=password&username=" + username + "&password=" + password;
		// requete api
		try {
			url = new URL("https://code.telecomste.fr/oauth/token");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", " application/x-www-form-urlencoded;charset=UTF-8");
			try (OutputStream os = conn.getOutputStream()) {
				OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
				osw.write(jsonStringauth);
				osw.flush();
				osw.close();
				os.close();
				System.out.println(jsonStringauth);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(conn.getResponseCode());
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			bearertoken = in.readLine();
			System.out.println(bearertoken);
			if (conn.getResponseCode() == 200) {
				accept = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accept;
	}

	public static String getUsername() {
		return username;
	}

	public static Boolean getAccept() {
		return accept;
	}

	public static void setUsername(String user) {
		username = user;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String pass) {
		password = pass ;
	}
	
	public static String getToken() {
		return bearertoken;
	}
	
	public static void setAcceptFalse() {
		accept = false ;
	}
	
	public static String getAutoconnect() {
		return autoconnect;
	}
	
	public static void setAutoconnect(String pass) {
		autoconnect = pass ;
	}

	public static void setCle(String pass) {
		cle = pass ;
	}
	
	public static String  getCle() {
		return cle ;
	}
	
	public static GitLabApi connectGitlabApi() {
		GitLabApi gitLabApi = null ;
		if ((Authentification.getAutoconnect()).equals("yes")) {
			gitLabApi = new GitLabApi("https://code.telecomste.fr", Authentification.getCle());
		}
		else {
			try {
				gitLabApi = GitLabApi.oauth2Login("https://code.telecomste.fr", Authentification.getUsername(), Authentification.getPassword());
			} catch (GitLabApiException e) {
				e.printStackTrace();
			}
		}
		return gitLabApi;
		
	}
}
