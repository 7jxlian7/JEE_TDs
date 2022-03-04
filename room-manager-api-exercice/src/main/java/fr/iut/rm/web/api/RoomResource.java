package fr.iut.rm.web.api;

import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;
import fr.iut.rm.persistence.dao.AccessEventDao;
import fr.iut.rm.persistence.dao.RoomDao;
import fr.iut.rm.persistence.domain.AccessEvent;
import fr.iut.rm.persistence.domain.EventType;
import fr.iut.rm.persistence.domain.Room;
import fr.iut.rm.web.api.model.AccessEventVO;
import fr.iut.rm.web.api.model.RoomDetailsVO;
import fr.iut.rm.web.api.model.RoomVO;
import fr.iut.rm.web.api.model.SaveRoomVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Room WS Endpoint
 */
@Path("/rooms")
@RequestScoped
public class RoomResource {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(RoomResource.class);

    /**
     * Injected Room DAO
     */
    @Inject
    private RoomDao roomDao;
    /**
     * Injected Access Event DAO
     */
    @Inject
    private AccessEventDao accessEventDao;

    /**
     * List all rooms
     *
     * @return all defined rooms
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    public List<RoomVO> listRooms(@QueryParam("q") String query) {
        logger.debug("List all rooms");
        // TODO add a query parameter (@QueryParam) to filter on room names using roomDao.searchByName method

        List<Room> rooms;

        // Retrieve rooms from DB
        if(query != null)
            rooms = roomDao.searchByName(query);
        else
            rooms = roomDao.findAll();

        // Convert rooms into visual object (select only necessary fields)
        final List<RoomVO> roomsVO = rooms.stream().map(room -> {
            final RoomVO roomVO = new RoomVO();
            roomVO.setId(room.getId());
            roomVO.setName(room.getName());
            roomVO.setDescription(room.getDescription());
            return roomVO;
        }).collect(Collectors.toList());

        //Return objects
        return roomsVO;
    }

    /**
     * Retrieve a room by its name.
     *
     * @param roomId room identifier
     * @return found room
     */
    @GET
    @Path("/{roomId}")
    @Produces({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    public RoomDetailsVO getRoom(@PathParam(value = "roomId") long roomId) {
        logger.debug("Retrieve room with id {}", roomId);

        Room r = roomDao.get(roomId);
        if(r == null)
            return null;


        int accessEventCount = accessEventDao.findBy(r.getId(), null).size();

        RoomDetailsVO roomDetailsVO = new RoomDetailsVO();
        roomDetailsVO.setName(r.getName());
        roomDetailsVO.setDescription(r.getDescription());
        roomDetailsVO.setId(r.getId());
        roomDetailsVO.setAccessEventsCount(accessEventCount);

        return roomDetailsVO;
    }

    /**
     * Creates a Room
     *
     * @param roomVO room object to create
     * @return Response :
     * - 400 if given room contains bad values
     * - 409 if a room already exists with the given name
     * - 200 with the created room id in case of success
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    public Response createRoom(SaveRoomVO roomVO) {
        logger.debug("Create a room");

        // TODO check mandatory parameters -> http status 400
        if(roomVO.name == null || roomVO.description == null)
            return Response.status(400).build();

        // Check if the room already exists with this name
        if(roomDao.findByName(roomVO.name) != null)
            return Response.status(409).build();

        // Create a room
        Room r = new Room();
        r.setName(roomVO.name);
        r.setDescription(roomVO.description);
        roomDao.saveOrUpdate(r);

        // Return 200
        return Response.ok().build();
    }

    /**
     * Creates an AccessEvent
     *
     * @param accessEventVO accessEvent to apply
     * @param roomId room id to access
     * @return Response :
     * - 404 if given room doesn't exist
     * - 200 with the created room id in case of success
     */
    @POST
    @Path("/{roomId}/events")
    @Consumes({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    public Response accessRoom(AccessEventVO accessEventVO, @PathParam(value = "roomId") long roomId) {
        logger.debug("Access room {}", roomId);

        Room r = roomDao.get(roomId);

        if(r == null)
            return Response.status(404).build();

        if(accessEventVO.getType() == null || accessEventVO.getUserName() == null)
            return Response.status(400).build();

        if(accessEventVO.getType() != EventType.IN && accessEventVO.getType() != EventType.OUT)
            return Response.status(401).build();

        AccessEvent ae = new AccessEvent();
        ae.setRoom(r);
        ae.setDateTime(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        ae.setUserName(accessEventVO.getUserName());
        ae.setType(accessEventVO.getType());
        accessEventDao.saveOrUpdate(ae);

        return Response.ok().build();
    }

    /**
     * List all accessEvents for a room
     *
     * @return all defined accessEvents
     */
    @GET
    @Path("/{roomId}/events")
    @Produces({MediaType.APPLICATION_JSON + "; charset=UTF-8"})
    public List<AccessEventVO> listRooms(@PathParam(value = "roomId") long roomId) {
        logger.debug("List all rooms");

        List<AccessEvent> accessEvents;

        Room r = roomDao.get(roomId);

        if(r == null)
            return null;

        accessEvents = accessEventDao.findBy(r.getId(), null);

        final List<AccessEventVO> accessEventVO = accessEvents.stream().map(ae -> {
            final AccessEventVO aeVO = new AccessEventVO();
            aeVO.setType(ae.getType());
            aeVO.setUserName(ae.getUserName());
            return aeVO;
        }).collect(Collectors.toList());

        return accessEventVO;
    }
}