package control;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.Funcionar;

public class Sistema {
	private static final String FILE_PATH = "bbdd.json";

	public static void main(String[] args) {
		
			Scanner sc = new Scanner(System.in);
			System.out.println("Bienvenido al sistema de inicio de sesión");
			int choice = 0;
			boolean continuar = true;
			while (continuar) {
				System.out.println("\nSeleccione una opción:");
				System.out.println("1. Iniciar sesión");
				System.out.println("2. Registrar usuario");
				System.out.println("3. Salir");
				try {
				choice = Integer.parseInt(sc.nextLine());
				}catch(Exception e) {
					
				}
				
				switch (choice) {
				case 1:
					continuar = login(sc);
					break;
				case 2:
					registerUser(sc);
					break;
				case 3:
					System.out.println("Gracias por usar el sistema de inicio de sesión. ¡Hasta luego!");
					continuar = false;
					break;
				default:
					System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
					break;
				}
				
			}
			
			sc.close();
	}

	private static boolean login(Scanner scanner) {
			JSONArray listaUsuarios = new JSONArray();
			JSONObject usuario = new JSONObject();
			boolean existe = false;
			boolean usu = false;
			boolean admin = false;
			
			System.out.println("\nIngrese su nombre de usuario:");
			String username = scanner.nextLine();

			System.out.println("Ingrese su contraseña:");
			String password = scanner.nextLine();

			JSONObject bbdd = readUsersFromFile();

			listaUsuarios = (JSONArray) bbdd.get("USUARIOS");

			for (int i = 0; i < listaUsuarios.size(); i++) {

				usuario = (JSONObject) listaUsuarios.get(i);

				if (usuario.get("usuario").toString().equals(username) && usuario.get("contrasenia").equals(password)) {
					if (usuario.get("role").equals("usuario")) {
						usu = true;
					} else if (usuario.get("role").equals("administrador")) {
						admin = true;
					} 
						
					i = listaUsuarios.size();
					existe = true;

				}

			}
			
			if (!existe) {
				System.out.println("El nombre de usuario o la contraseña es incorrecto. Vuelve a intentarlo o regístrate.");
				return true;
			} else {
				System.out.println("Inicio de sesión exitoso. ¡Bienvenido, " + username+"!");
				if (usu) {
					Funcionar.funcionarUsu(bbdd, usuario);
				
				} else if (admin) {
					Funcionar.funcionarAdmin(bbdd, usuario);
				} else 	{
					Funcionar.funcionarSeg(bbdd, usuario);
				}
				
				
				
			}
			return false;
			 
	}

	@SuppressWarnings("unchecked")
	private static void registerUser(Scanner scanner) {
			JSONArray listaUsuarios = new JSONArray();
			JSONObject usuario = new JSONObject();
			boolean existe = false;

			System.out.println("\nIngrese un nombre de usuario:");
			String username = scanner.nextLine();

			System.out.println("Ingrese una contraseña:");
			String password = scanner.nextLine();
			
			System.out.println("Ingrese su matricula:");
			String matricula = scanner.nextLine();

			// Devuelve la base de datos completa
			JSONObject bbdd = readUsersFromFile();

			listaUsuarios = (JSONArray) bbdd.get("USUARIOS");

			for (int i = 0; i < listaUsuarios.size(); i++) {

				usuario = (JSONObject) listaUsuarios.get(i);

				if (usuario.get("usuario").toString().equals(username)) {
					i = listaUsuarios.size();
					existe = true;

				}

			}

			if (existe) {
				System.out.println("El nombre de usuario '" + username + "' ya existe. Por favor, elija otro nombre de usuario.");
			} else {
				JSONObject newUser = new JSONObject();
				newUser.put("usuario", username);
				newUser.put("contrasenia", password);
				newUser.put("matricula", matricula);
				newUser.put("role", "usuario");
				
				
				listaUsuarios.add(newUser);

				bbdd.put("USUARIOS", listaUsuarios);

				writeUsersToFile(bbdd);

				System.out.println(
						"Usuario registrado correctamente. Ahora puedes iniciar sesión con tu nombre de usuario y contraseña.");
			}

	}

	private static JSONObject readUsersFromFile() {
		JSONParser parser = new JSONParser();
		JSONObject users = new JSONObject();

		try (FileReader reader = new FileReader(FILE_PATH)) {
			Object obj = parser.parse(reader);
			users = (JSONObject) obj;
		} catch (IOException | ParseException e) {
			// Manejo de excepciones en caso de error de lectura
			e.printStackTrace();
		}
		return users;
	}

	private static void writeUsersToFile(JSONObject users) {
		try (FileWriter file = new FileWriter(FILE_PATH)) {
			file.write(users.toJSONString());
			file.close();
		} catch (IOException e) {
			// Manejo de excepciones en caso de error de escritura
			e.printStackTrace();
		}
	}
}
