package backend.simulacionsuquilanda.Servicios;

import backend.simulacionsuquilanda.Gestion.UsuarioGestion;
import backend.simulacionsuquilanda.Objetos.Usuario;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuarios")
public class UsuarioService {

    @Inject
    private UsuarioGestion usuarioGestion;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearUsuario(Usuario usuario) {
        try {
            usuarioGestion.crearUsuario(usuario); // Llama al método de gestión para guardar el usuario
            return Response.status(Response.Status.CREATED)
                    .entity(usuario)
                    .header("Access-Control-Allow-Origin", "http://localhost:4200")
                    .header("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
                    .header("Access-Control-Allow-Headers", "Content-Type, Authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .build(); // Devuelve el usuario creado
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error al crear el usuario : " + e.getMessage())
                    .header("Access-Control-Allow-Origin", "http://localhost:4200")
                    .header("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
                    .header("Access-Control-Allow-Headers", "Content-Type, Authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .build();
        }
    }

    @GET
    @Path("/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerUsuarioPorCedula(@PathParam("cedula") String cedula) {
        Usuario usuario = usuarioGestion.obtenerUsuarioPorCedula(cedula);
        if (usuario != null) {
            return Response.ok(usuario)
                    .header("Access-Control-Allow-Origin", "http://localhost:4200")
                    .header("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
                    .header("Access-Control-Allow-Headers", "Content-Type, Authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuario no encontrado")
                    .header("Access-Control-Allow-Origin", "http://localhost:4200")
                    .header("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
                    .header("Access-Control-Allow-Headers", "Content-Type, Authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .build();
        }
    }

    @OPTIONS
    @Path("/{cedula}")
    public Response optionsForGet() {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "http://localhost:4200")
                .header("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
                .header("Access-Control-Allow-Headers", "Content-Type, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .build();
    }
}
