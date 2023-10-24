package API

import retrofit2.Call
import retrofit2.http.GET


class ApiService {

    interface ApiService {
        @GET("weather/current") // Substitua "weather/current" pelo endpoint da sua API
        fun getWeatherData(): Call<WeatherData> // Substitua "WeatherData" pela classe de modelo correspondente
    }

    data class WeatherData(
        val temperature: Double,
        val description: String
    )


}