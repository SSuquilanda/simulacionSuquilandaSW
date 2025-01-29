package backend.simulacionsuquilanda.Servicios;

import backend.simulacionsuquilanda.Gestion.ConsumoGestion;
import backend.simulacionsuquilanda.Objetos.Consumo;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/consumos")
public class ConsumoService {

    @Inject
    private ConsumoGestion consumoGestion;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearConsumo(Consumo consumo) {
        try {
            consumoGestion.crearConsumo(consumo);
            return Response.status(Response.Status.CREATED)
                    .entity(consumo)
                    .header("Access-Control-Allow-Origin", "http://localhost:4200")
                    .header("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
                    .header("Access-Control-Allow-Headers", "Content-Type, Authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error al crear el consumo : " + e.getMessage())
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
    public Response listarConsumos(@PathParam("cedula") String cedula) {
        List<Consumo> consumos = consumoGestion.listarConsumos(cedula);
        if (consumos != null) {
            return Response.ok(consumos)
                    .header("Access-Control-Allow-Origin", "http://localhost:4200")
                    .header("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
                    .header("Access-Control-Allow-Headers", "Content-Type, Authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("usuario no encontrado")
                    .header("Access-Control-Allow-Origin", "http://localhost:4200")
                    .header("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
                    .header("Access-Control-Allow-Headers", "Content-Type, Authorization")
                    .header("Access-Control-Allow-Credentials", "true")
                    .build();
        }
    }

    @OPTIONS
    @Path("/{cedula}")
    public Response optionsForListarConsumos() {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "http://localhost:4200")
                .header("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
                .header("Access-Control-Allow-Headers", "Content-Type, Authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .build();
    }
}
