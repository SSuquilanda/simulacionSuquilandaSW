package backend.simulacionsuquilanda.Servicios;

import backend.simulacionsuquilanda.Gestion.ConsumoGestion;
import backend.simulacionsuquilanda.Gestion.UsuarioGestion;
import backend.simulacionsuquilanda.Objetos.Consumo;
import backend.simulacionsuquilanda.Objetos.Usuario;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/consulta")
public class ConsultaService {

    @Inject
    private UsuarioGestion usuarioGestion;

    @Inject
    private ConsumoGestion consumoGestion;

    @GET
    @Path("/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerDatosCompletos(@PathParam("cedula") String cedula) {
        // Buscar usuario por cédula
        Usuario usuario = usuarioGestion.obtenerUsuarioPorCedula(cedula);

        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuario no encontrado")
                    .header("Access-Control-Allow-Origin", "http://localhost:4200")
                    .header("Access-Control-Allow-Methods", "GET, OPTIONS")
                    .header("Access-Control-Allow-Headers", "Content-Type, Authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .build();
        }

        // Buscar consumos por usuario_id
        List<Consumo> consumos = consumoGestion.listarConsumosPorUsuarioId(usuario.getId());

        // Crear objeto de respuesta con datos de usuario y consumos
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("usuario", usuario);
        respuesta.put("consumos", consumos);

        return Response.ok(respuesta)
                .header("Access-Control-Allow-Origin", "http://localhost:4200")
                .header("Access-Control-Allow-Methods", "GET, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .build();
    }
}
