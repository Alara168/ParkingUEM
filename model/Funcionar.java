package model;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import comparador.Organizar;

public class Funcionar {

	public Funcionar() {
	}

	public static void funcionarUsu(JSONObject bbdd, JSONObject usuario) {
		JSONObject parking = (JSONObject) bbdd.get("PARKING");
		JSONArray listaParking = (JSONArray) parking.get("Plazas");
		boolean terminar = true;

		Scanner sc = new Scanner(System.in);
		do {

			System.out.println("\n------------------- MENU -------------------");
			System.out.println("Para ver las plazas libres, pulse 1");
			System.out.println("Para coger una plaza, pulse 2");
			System.out.println("Para encontrar un coche, pulse 3");
			System.out.println("Para desocupar una plaza, pulse 4");
			System.out.println("Para salir, pulse 5");
			int opcion = 0;
			try {
				opcion = Integer.parseInt(sc.nextLine());
				}catch(Exception e) {
					
				}
			switch (opcion) {
			case 1: {
				verPlazas(listaParking);

				break;
			}
			case 2: {
				ocuparPlaza(bbdd, usuario, sc, parking, listaParking);

				break;
			}
			case 3: {
				verTuPlaza(usuario, listaParking);

				break;
			}
			case 4: {

				desocuparPlaza(bbdd, usuario, sc, parking, listaParking);

				break;
			}
			case 5: {
				terminar = false;
				System.out.println("Gracias por usar nuestro sistema. ¡Hasta luego!");
				break;
			}
			default: {
				System.out.println("Introduzca una opción válida");
			}
			}
		} while (terminar);

	}

	@SuppressWarnings("unchecked")
	private static void desocuparPlaza(JSONObject bbdd, JSONObject usuario, Scanner sc, JSONObject parking,
			JSONArray listaParking) {
		JSONObject obj;

		String respuesta = "";
		boolean continuar = true;
		int posCoche = 0;

		while (continuar) {
			if (tienesYaPlaza(usuario, listaParking)) {
				obj = recogerCoche(usuario, listaParking);
				System.out.println("¿Estas seguro? (s/n)");
				respuesta = sc.nextLine();
				if (respuesta.equals("s")) {
					try (FileWriter fw = new FileWriter("bbdd.json")) {
						posCoche = listaParking.indexOf(obj);
						obj.remove("matricula_veh");
						obj.put("disponible", true);

						listaParking.set(posCoche, obj);
						parking.put("Plazas", listaParking);
						bbdd.put("PARKING", parking);
						fw.write(bbdd.toString());
						continuar = false;
						System.out.println("Su plaza se ha desocupado");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					// TODO: hacer filtro mas estricto de si se pulsa s o n y si se pulsa otra
					// cosa decir que eres tonto basicamente, (HACER LO MISMO EN EL METODO
					// OCUPARCOCHE())
					continuar = false;
				}

			} else {
				System.out.println("No tienes una plaza");
				continuar = false;
			}
		}

	}

	private static void verTuPlaza(JSONObject usuario, JSONArray listaParking) {
		JSONObject obj;
		boolean existe = false;
		for (int i = 0; i < listaParking.size(); i++) {
			obj = (JSONObject) listaParking.get(i);

			if (obj.get("matricula_veh") != null && obj.get("matricula_veh").equals(usuario.get("matricula"))) {
				i = listaParking.size();
				existe = true;
				System.out.println("Tu plaza es: " + obj.get("id").toString());
			}

		}

		if (!existe) {
			System.out.println("No has ocupado ninguna plaza");
		}
	}

	private static void verPlazas(JSONArray listaParking) {
		JSONObject obj;
		for (int i = 0; i < listaParking.size(); i++) {
			obj = (JSONObject) listaParking.get(i);

			if (obj.get("disponible").equals(true)) {
				System.out.println("Plaza: " + obj.get("id"));
			}

		}
	}

	@SuppressWarnings("unchecked")
	private static void ocuparPlaza(JSONObject bbdd, JSONObject usuario, Scanner sc, JSONObject parking,
			JSONArray listaParking) {
		JSONObject obj = new JSONObject();

		boolean continuar = true;
		String respuesta = "";
		int numPlaza = 0;

		while (continuar) {
			if (!tienesYaPlaza(usuario, listaParking)) {
			System.out.println("Introduce el id de la plaza la cual quieras ocupar");
			try {
				numPlaza = Integer.parseInt(sc.nextLine());
				}catch(Exception e) {
					
				}


			if (numPlaza < 1 || numPlaza > 400) {
				System.out.println("Introduzca una valor válido de 0 a 400\n");
			} else {
				obj = (JSONObject) listaParking.get(numPlaza - 1);
				if (obj.get("disponible").equals(true)) {

					
						try (FileWriter fw = new FileWriter("bbdd.json")) {
							if (obj.get("veces_ocupada") != null) {
								obj.put("veces_ocupada", Integer.parseInt(obj.get("veces_ocupada").toString()) + 1);
							} else {
								obj.put("veces_ocupada", 1);
							}

							obj.put("disponible", false);
							obj.put("matricula_veh", usuario.get("matricula").toString());

							listaParking.set(numPlaza - 1, obj);

							parking.put("Plazas", listaParking);

							bbdd.put("PARKING", parking);

							fw.write(bbdd.toString());

							System.out.println("Se ha actualizado la plaza: " + numPlaza);
							continuar = false;
						} catch (Exception e) {

						}

					

				} else {
					System.out.println("Esa plaza ya esta ocupada. Quieres introducir otra (s/n)?");
					respuesta = sc.nextLine();

					if (respuesta.equals("n")) {
						continuar = false;
					}
					
				}
			}

		} else {
			System.out.println("Ya tiene una plaza\nPor favor desocupe la plaza antes de elegir otra");
			
			continuar = false;
		}

	}
}

	private static boolean tienesYaPlaza(JSONObject usuario, JSONArray listaParking) {
		boolean tienePlaza = false;
		JSONObject plaza = new JSONObject();

		for (int i = 0; i < listaParking.size(); i++) {
			plaza = (JSONObject) listaParking.get(i);

			if (plaza.get("matricula_veh") != null && plaza.get("matricula_veh").equals(usuario.get("matricula"))) {
				i = listaParking.size();
				tienePlaza = true;
			}

		}

		return tienePlaza;
	}

	private static JSONObject recogerCoche(JSONObject usuario, JSONArray listaParking) {
		JSONObject plaza = new JSONObject();

		for (int i = 0; i < listaParking.size(); i++) {
			plaza = (JSONObject) listaParking.get(i);

			if (plaza.get("matricula_veh") != null && plaza.get("matricula_veh").equals(usuario.get("matricula"))) {
				i = listaParking.size();

			}

		}

		return plaza;
	}

	public static void funcionarSeg(JSONObject bbdd, JSONObject usuario) {
		boolean terminar = false;
		JSONObject parking = (JSONObject) bbdd.get("PARKING");
		JSONArray listaParking = (JSONArray) parking.get("Plazas");
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("\n------------------- MENU -------------------");
			System.out.println("Para cambiar el estado de una plaza, pulse 1");
			System.out.println("Para salir, pulse 2");

			String opcion = sc.nextLine();
			switch (opcion) {
			case "1": {
				cambiarEstado(bbdd, parking, listaParking, sc);
				break;

			}

			case "2": {
				terminar = true;
				break;
			}
			default: {
				System.out.println("Introduzca una opción válida");
			}
			}
		} while (terminar == false);
	}

	@SuppressWarnings({ "unchecked" })
	private static void cambiarEstado(JSONObject bbdd, JSONObject parking, JSONArray listaParking, Scanner sc) {
		int plaza = 0;
		int posPlaza;
		JSONObject obj;
		verPlazasOcupadas(listaParking);
		System.out.println("\nIntroduce el id que quieras cambiar");
		
		try {
			plaza = Integer.parseInt(sc.nextLine());
			}catch(Exception e) {
				System.out.println("Por favor introduzca un valor correcto");
			}
		
		

		if (plaza < 0 || plaza > 400) {
			System.out.println("No se puede meter una plaza que esté por debajo del 0 " + "y por encima del 400");
		} else {

			try (FileWriter fw = new FileWriter("bbdd.json")) {
				for (int i = 0; i < listaParking.size(); i++) {
					obj = (JSONObject) listaParking.get(i);
					posPlaza = listaParking.indexOf(obj);
					if (Integer.parseInt(obj.get("id").toString()) == plaza) {
						i = listaParking.size();

						if (obj.get("disponible").equals(false)) {
							obj.remove("matricula_veh");
							obj.put("disponible", true);
						}
						else {
							System.out.println("La plaza está vacía");
						}

						listaParking.set(posPlaza, obj);

						parking.put("CambiosSeg", Integer.parseInt(parking.get("CambiosSeg").toString()) + 1);
						parking.put("Parking", listaParking);
						bbdd.put("PARKING", parking);
						fw.write(bbdd.toString());

					}

				}
			} catch (Exception e) {

			}

		}
	}

	private static void verPlazasOcupadas(JSONArray listaParking) {
		JSONObject obj;
		System.out.println("Las plazas ocupadas son:");
		for (int i = 0; i < listaParking.size(); i++) {
			obj = (JSONObject) listaParking.get(i);

			if (obj.get("disponible").equals(false)) {
				System.out.println("Plaza: " + obj.get("id"));
			}

		}
	}

	@SuppressWarnings("resource")
	public static void funcionarAdmin(JSONObject bbdd, JSONObject usuario) {
		boolean terminar = false;
		Scanner sc = new Scanner(System.in);
		JSONObject parking = (JSONObject) bbdd.get("PARKING");
		JSONArray listaParking = (JSONArray) parking.get("Plazas");
		JSONObject obj = new JSONObject();
		TreeMap<Integer, Integer> lista = new TreeMap<>();
		TreeMap<Integer, Integer> listaOrdenada = new TreeMap<>();
		TreeMap<Integer, Integer> listaOrdenadaInversa = new TreeMap<>();
		

		for (int i = 0; i < listaParking.size(); i++) {
			obj = (JSONObject) listaParking.get(i);

			if (obj.get("veces_ocupada") != null) {
				lista.put(Integer.parseInt(obj.get("id").toString()),
						Integer.parseInt(obj.get("veces_ocupada").toString()));
			}

		}

		listaOrdenada = Organizar.valueSort(lista);
		listaOrdenadaInversa = Organizar.valueSortInverse(lista);

		do {
			System.out.println("\n------------------- MENU -------------------");
			System.out.println("Para ver las estadísticas, pulse 1");
			System.out.println("Para salir, pulse 2");

			String opcion = sc.nextLine();
			switch (opcion) {

			case "1": {
			
				//verEstadisticas(parking, listaOrdenada);
				verEstadisticas(parking, listaOrdenada, listaOrdenadaInversa);

			}
				break;

			case "2": {
				terminar = true;
				System.out.println("Gracias por usar nuestro sistema. ¡Hasta luego!");
				break;
			}
			default: {
				System.out.println("Introduzca una opción válida");
			}
			}
		} while (terminar == false);

	}

	private static void verEstadisticas(JSONObject parking, TreeMap<Integer, Integer> listaOrdenada, TreeMap<Integer, Integer> listaOrdenadaInversa) {
		
		System.out.println("Se han realizado " + parking.get("CambiosSeg").toString() + " cambios");
		System.out.println("\nLas 5 plazas más cogidas son: ");
		int cont = 0;
		int contador = 0;
		Set<Entry<Integer, Integer>> set = listaOrdenada.entrySet();
		Iterator<Entry<Integer, Integer>> i = set.iterator();
		while (i.hasNext() && contador < 5) {
			@SuppressWarnings("rawtypes")
			Map.Entry mp = (Map.Entry) i.next();

			System.out.println("	Plaza: " + mp.getKey() + " ha sido ocupada " + mp.getValue() + " veces");
			contador++;
		}
		System.out.println("\nLas 5 plazas menos cogidas son: ");
		Set<Entry<Integer, Integer>> se = listaOrdenadaInversa.entrySet();
		Iterator<Entry<Integer, Integer>> j = se.iterator();
		while (j.hasNext() && cont < 5) {
			@SuppressWarnings("rawtypes")
			Map.Entry mp1 = (Map.Entry) i.next();

			System.out.println("	Plaza: " + mp1.getKey() + " ha sido ocupada " + mp1.getValue() + " veces");
			cont++;
		}
		
	}

}
