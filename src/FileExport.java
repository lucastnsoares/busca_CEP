import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileExport {
    public void exportToJson(List<Endereco> listaDeEnderecos) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        try {
            FileWriter file = new FileWriter("listaCeps.json");
            file.write(gson.toJson(listaDeEnderecos));
            file.close();
            System.out.println("Arquivo 'listaCeps.json' gerado com sucesso, na pasta raiz do programa.");
        } catch (IOException e) {
            throw new FileExportException("Erro ao gerar arquivo: " + e.getMessage());
        }
    }
}
