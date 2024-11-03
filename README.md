# Boat API for Dancerace

Hi and welcome to the Dancerace boat api

## Installation (Maven)

```bash
nvm clean install
```

## Run the API

use com.example.antony.BoatApplication to run the application. When you first run this application it will auto load the
in memory DB with some sample data.

## Example Requests

````
curl --location --request GET 'localhost:8080/api/boats'
curl --location --request GET 'localhost:8080/api/boats/?name=Bilboat%20Baggins'
curl --location --request DELETE 'localhost:8080/api/boats/1' 
curl --location --request PUT 'localhost:8080/api/boats/1' \
--data '{name": "smile and wave boys, smile and wave"}'
````

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

[MIT](https://choosealicense.com/licenses/mit/)