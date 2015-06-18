package br.com.ricardonm.gotadagua.wrapper;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.util.HashMap;

/**
 * Created by ricardomiranda on 17/06/15.
 */
public class WrapperUtil {

    private void getJson(final String url) {

        // Toda chamada externa necessita rodar em background, então utilizamos thread

        new Thread(new Runnable() {
            @Override
            public void run() {
                // Criamos nosso objeto de retorno que poderia ser uma entidade (Exemplo: Aluno, Usuário, etc.), nesse caso utilizamos algo genérico.
                Object retorno = null;
                // Há a necessidade de tratarmos excessão tendo em vista que estamos realizando requisições em nossa aplicação
                try {
                    // Setamos o cliente http e o nosso request, que será do tipo GET (O POST veremos em outros artigos)
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpGet request = new HttpGet();

                    // Setamos nossa URI

                    request.setURI(new URI(url));

                    // Executamos nossa transação HTTP

                    HttpResponse response = httpclient.execute(request);

                    // Pegamos o conteúdo advindo como resposta e inserimos em um InputStream

                    InputStream content = response.getEntity().getContent();

                    // Instanciamos o nosso Reader com o InputStream

                    Reader reader = new InputStreamReader(content);

                    // Aqui vamos utilizar a Biblioteca Gson para transformar o Json recebido em Objeto JAVA

/* Instanciamos o objeto Gson e em seguida utilizamos o método fromJson() passando como parâmetro o Reader instanciado e o tipo do Objeto que será retornado. */

                    Gson gson = new Gson();
                    retorno = gson.fromJson(reader, HashMap.class);

                    content.close();

                } catch (Exception e) {
                    e.printStackTrace();

                }

            }

        }).start();

    }

}
