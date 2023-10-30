    package com.example.jumppark;
    import androidx.appcompat.app.AppCompatActivity;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.content.Intent;
    import android.widget.Toast;
    import API.AuthResponse;
    import API.AuthService;
    import API.LoginRequest;
    import retrofit2.Call;
    import retrofit2.Callback;
    import retrofit2.Response;
    import retrofit2.Retrofit;
    import retrofit2.converter.gson.GsonConverterFactory;

    public class Login extends AppCompatActivity {

        private EditText etUsername, etPassword;
        private Button btnLogin;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            etUsername = findViewById(R.id.etUsername);
            etPassword = findViewById(R.id.etPassword);
            btnLogin = findViewById(R.id.btnLogin);

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = etUsername.getText().toString();
                    String password = etPassword.getText().toString();

                    if (!username.isEmpty() && !password.isEmpty()) {
                        // Crie uma instância do Retrofit com a URL base correta
                        Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl("https://dev.app.jumpparkapi.com.br/api/") // Substitua pela URL base correta
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        // Crie uma instância do serviço AuthService
                        AuthService authService = retrofit.create(AuthService.class);

                        LoginRequest loginRequest = new LoginRequest(username, password);
                        // Adicione o token de autenticação no cabeçalho
                        String bearerToken = "Bearer 86703d9d1359bd2ca375c6987011b617f383189bc018499acb05d3091d52841f";



                        // Faça a chamada para a API
                        // Faça a chamada para a API
                        Call<AuthResponse> call = authService.login(loginRequest);
                        call.enqueue(new Callback<AuthResponse>() {
                            @Override
                            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                                if (response.isSuccessful()) {
                                    // Autenticação bem-sucedida, faça o redirecionamento para a próxima tela.
                                    Intent intent = new Intent(Login.this, TelaInicial.class);
                                    startActivity(intent); // Adicione esta linha para iniciar a nova atividade
                                } else if (response.code() == 401) {
                                    // Credenciais inválidas, exiba uma mensagem de erro para o usuário
                                    etUsername.setError("Usuário ou senha inválidos");
                                    etPassword.setError("Usuário ou senha inválidos");
                                } else {
                                    // Outro erro, trate-o de acordo com o código de resposta
                                    showError("Erro na autenticação. Tente novamente mais tarde.");
                                }
                            }

                            @Override
                            public void onFailure(Call<AuthResponse> call, Throwable t) {
                                showError("Erro na autenticação. Verifique sua conexão com a internet ou e-mail e senha...");
                            }
                        });

                    }
                }
            });
        }

        // Método para exibir mensagens de erro
        private void showError(String message) {
            // Aqui você pode implementar a lógica para exibir uma mensagem de erro, como um Toast
            Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
        }
    }

