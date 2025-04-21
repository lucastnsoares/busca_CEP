public record Endereco (String cep, String logradouro, String complemento, String bairro, String localidade, String uf, String estado, String regiao) {

    @Override
    public String toString() {
        String mensagem = String.format("""
                -------------------------------------------
                
                CEP: %s
                Logradouro: %s
                Complemento: %s
                Bairro: %s
                Localidade: %s
                UF: %s
                Estado: %s
                Região: %s
                
                -------------------------------------------
                """, cep, logradouro, complemento, bairro, localidade, uf, estado, regiao);
        return mensagem;
    }
}
