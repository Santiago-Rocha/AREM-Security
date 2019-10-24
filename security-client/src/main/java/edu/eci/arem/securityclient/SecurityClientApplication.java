package edu.eci.arem.securityclient;

import java.io.InputStream;
import java.security.KeyStore;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SecurityClientApplication {

	public static void main(String[] args) {
		//spring.jpa.hibernate.ddl-auto=create
		SpringApplication.run(SecurityClientApplication.class, args);
	}

	/**
	 * Este metodo se encarga de establecer un cliente seguro para realizar peticiones a un servidor a traves de certificados SSL
	 * @return Cliente http configurado con ssl para acer peticiones sincronaz
	 */
	@Bean
	public RestTemplate getRestTemplate(){
		RestTemplate restTemplate =  new RestTemplate();
		KeyStore keyStore;
		HttpComponentsClientHttpRequestFactory requestFactory =  null;
		try{
			//almacena el certificado
			keyStore = KeyStore.getInstance("jks");
			ClassPathResource classPathResource = new ClassPathResource("nt-gateway.jks");
			InputStream inputStream = classPathResource.getInputStream();
			keyStore.load(inputStream, "Thruman98".toCharArray());

			//configura el certificado ssl que se va a intercambiar
			SSLConnectionSocketFactory socketFactory =  new SSLConnectionSocketFactory(
				new SSLContextBuilder().loadTrustMaterial(null, new TrustSelfSignedStrategy())
				.loadKeyMaterial(keyStore, "Thruman98".toCharArray()).build(),NoopHostnameVerifier.INSTANCE);
			
			//configura el cliente con el certificado 
			HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory)
						.setMaxConnTotal(Integer.valueOf(5))
						.setMaxConnPerRoute(Integer.valueOf(5))
						.build();
			
			requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
			requestFactory.setReadTimeout(Integer.valueOf(10000));
			requestFactory.setConnectTimeout(Integer.valueOf(1000));

			restTemplate.setRequestFactory(requestFactory);
		} catch(Exception ex){
			ex.printStackTrace();
		}

		return restTemplate;
	}


}

