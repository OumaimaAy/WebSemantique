package tn.sem.websem;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.*;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.apache.jena.vocabulary.RDF;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.update.UpdateAction;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateRequest;

@RestController
public class RestApi {

    Model model = JenaEngine.readModel("data/ecodev.owl");
    @GetMapping("/getEventByFeedback")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> afficherEvent() {
        try {
            String NS = "";
            if (model != null) {
                NS = model.getNsPrefixURI("");
                Model inferredModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");
                OutputStream res = JenaEngine.executeQueryFile(inferredModel, "data/query_Event.txt");
                System.out.println(res);
                return new ResponseEntity<>(res.toString(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Error when reading model from ontology", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getCenterByType")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> afficherCenterByType() {
        try {
            String NS = "";
            if (model != null) {
                NS = model.getNsPrefixURI("");
                Model inferredModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");
                OutputStream res = JenaEngine.executeQueryFile(inferredModel, "data/query_center.txt");
                System.out.println(res);
                return new ResponseEntity<>(res.toString(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Error when reading model from ontology", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getAgenceByNameSrv")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> afficherAgenceByNameSrv() {
        try {
            String NS = "";
            if (model != null) {
                NS = model.getNsPrefixURI("");
                Model inferredModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");
                OutputStream res = JenaEngine.executeQueryFile(inferredModel, "data/query_DeliveryAgence.txt");
                System.out.println(res);
                return new ResponseEntity<>(res.toString(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Error when reading model from ontology", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getPubByContentMsg")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> afficherPubByContentMsg() {
        try {
            String NS = "";
            if (model != null) {
                NS = model.getNsPrefixURI("");
                Model inferredModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");
                OutputStream res = JenaEngine.executeQueryFile(inferredModel, "data/query_publication.txt");
                System.out.println(res);
                return new ResponseEntity<>(res.toString(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Error when reading model from ontology", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getProdByPerc")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> afficherProdByPerc() {
        try {
            String NS = "";
            if (model != null) {
                NS = model.getNsPrefixURI("");
                Model inferredModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");
                OutputStream res = JenaEngine.executeQueryFile(inferredModel, "data/query_recycledProd.txt");
                System.out.println(res);
                return new ResponseEntity<>(res.toString(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Error when reading model from ontology", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getWasteByCategory")
    @CrossOrigin(origins = "http://localhost:8000")
    public ResponseEntity<String> afficherWasteByCategory() {
        try {
            String NS = "";
            if (model != null) {
                NS = model.getNsPrefixURI("");
                Model inferredModel = JenaEngine.readInferencedModelFromRuleFile(model, "data/rules.txt");
                OutputStream res = JenaEngine.executeQueryFile(inferredModel, "data/query_waste.txt");
                System.out.println(res);
                return new ResponseEntity<>(res.toString(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Error when reading model from ontology", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Internal Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

///***********//
@GetMapping("/getAllEvents")
@CrossOrigin(origins = "http://127.0.0.1:8000")
public ResponseEntity<List<EventDto>> getAllEvents() {
    List<EventDto> events = new ArrayList<>();

    if (model != null) {
        try {
            // Define the URI for Event type
            Resource eventType = model.createResource("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#Event");

            // Define properties for event attributes
            Property titleProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#title");
            Property descriptionProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#description");
            Property dateProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#date");
            Property locationProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#location");
            Property organizerProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#organizer");
            Property maxParticipantsProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#maxParticipants");
            Property imageProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#image");

            // First, iterate over all NamedIndividuals
            StmtIterator namedIndividuals = model.listStatements(null, RDF.type, (RDFNode) null);
            while (namedIndividuals.hasNext()) {
                Statement statement = namedIndividuals.nextStatement();
                Resource eventResource = statement.getSubject();

                // Check if it's an event based on URI or rdf:type
                if (eventResource.getURI() != null && eventResource.getURI().contains("Event")) {
                    // Add the event to the list (both with and without rdf:type)
                    addEventToList(eventResource, titleProperty, descriptionProperty, dateProperty, locationProperty,
                            organizerProperty, maxParticipantsProperty, imageProperty, events);
                }
            }

            return new ResponseEntity<>(events, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error retrieving events: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } else {
        System.err.println("Model is null - Error when reading model from ontology");
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

    // Helper method to add event to the list
    private void addEventToList(Resource eventResource, Property titleProperty, Property descriptionProperty, Property dateProperty,
                                Property locationProperty, Property organizerProperty, Property maxParticipantsProperty,
                                Property imageProperty, List<EventDto> events) {
        String title = getLiteralValue(eventResource, titleProperty);
        String description = getLiteralValue(eventResource, descriptionProperty);
        String dateString = getLiteralValue(eventResource, dateProperty);
        String location = getLiteralValue(eventResource, locationProperty);
        String organizer = getLiteralValue(eventResource, organizerProperty);
        String maxParticipants = getLiteralValue(eventResource, maxParticipantsProperty);
        String image = getLiteralValue(eventResource, imageProperty);

        // Create an EventDto instance and set its properties
        EventDto eventDto = new EventDto();
        eventDto.setEvent(eventResource.getURI());
        eventDto.setTitle(title);
        eventDto.setDescription(description);

        // Parse the date safely to LocalDateTime
        if (dateString != null) {
            try {
                eventDto.setDate(LocalDateTime.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            } catch (DateTimeParseException e) {
                System.err.println("Error parsing date: " + dateString);
                eventDto.setDate(null);  // Set to null if the date cannot be parsed
            }
        } else {
            eventDto.setDate(null);
        }

        eventDto.setLocation(location);
        eventDto.setOrganizer(organizer);

        // Parse maxParticipants safely
        try {
            eventDto.setMax_participants(maxParticipants != null ? Integer.parseInt(maxParticipants) : 0);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing maxParticipants: " + maxParticipants);
            eventDto.setMax_participants(0);
        }

        eventDto.setImage(image);

        events.add(eventDto);
    }

    // Helper method to retrieve a literal value of a property
    private String getLiteralValue(Resource resource, Property property) {
        Statement statement = resource.getProperty(property);
        return statement != null ? statement.getObject().toString() : null;
    }


    @PostMapping("/addEvent")
    @CrossOrigin(origins = "http://127.0.0.1:8000")
    public ResponseEntity<String> addEvent(@RequestBody EventDto eventDto) {
        if (model != null) {
            try {
                // Générer une URI unique pour l'événement
                String eventUri = "http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#Event" + UUID.randomUUID().toString();

                // Créer la ressource Event avec l'URI générée
                Resource eventResource = model.createResource(eventUri);

                // Définir les propriétés pour l'entité Event
                Property titleProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#title");
                Property descriptionProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#description");
                Property dateProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#date");
                Property locationProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#location");
                Property organizerProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#organizer");
                Property maxParticipantsProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#maxParticipants");
                Property imageProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#image");

                // Ajouter le type RDF pour la ressource Event
                model.add(eventResource, RDF.type, model.createResource("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#Event"));

                // Ajouter les propriétés à la ressource
                model.add(eventResource, titleProperty, eventDto.getTitle());
                model.add(eventResource, descriptionProperty, eventDto.getDescription());
                model.add(eventResource, dateProperty, eventDto.getDate().toString());
                model.add(eventResource, locationProperty, eventDto.getLocation());
                model.add(eventResource, organizerProperty, eventDto.getOrganizer());
                model.add(eventResource, maxParticipantsProperty, Integer.toString(eventDto.getMax_participants()));
                model.add(eventResource, imageProperty, eventDto.getImage());

                // Sauvegarder le modèle
                JenaEngine.saveModel(model, "data/ecodev.owl");

                return new ResponseEntity<>("Event added successfully: " + eventUri, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>("Error adding Event: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Error when reading model from ontology", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/updateEvent")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> updateEvent(@RequestBody EventDto eventDto) {
        if (model != null) {
            try {
                // Récupérer la ressource Event en utilisant l'URI
                Resource eventResource = model.getResource(eventDto.getEvent()); // URI unique de l'événement à mettre à jour

                if (eventResource != null && eventResource.hasProperty(RDF.type, model.createResource("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#Event"))) {
                    // Supprimer les anciennes valeurs
                    Property titleProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#title");
                    Property descriptionProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#description");
                    Property dateProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#date");
                    Property locationProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#location");
                    Property organizerProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#organizer");
                    Property maxParticipantsProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#maxParticipants");
                    Property imageProperty = model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#image");

                    // Supprimer les anciennes propriétés (si présentes)
                    model.removeAll(eventResource, titleProperty, null);
                    model.removeAll(eventResource, descriptionProperty, null);
                    model.removeAll(eventResource, dateProperty, null);
                    model.removeAll(eventResource, locationProperty, null);
                    model.removeAll(eventResource, organizerProperty, null);
                    model.removeAll(eventResource, maxParticipantsProperty, null);
                    model.removeAll(eventResource, imageProperty, null);

                    // Ajouter les nouvelles valeurs
                    model.add(eventResource, titleProperty, eventDto.getTitle());
                    model.add(eventResource, descriptionProperty, eventDto.getDescription());
                    if (eventDto.getDate() != null) {
                        model.add(eventResource, dateProperty, eventDto.getDate().toString());
                    } else {
                        model.add(eventResource, dateProperty, "Date not provided");
                    }
                    model.add(eventResource, locationProperty, eventDto.getLocation());
                    model.add(eventResource, organizerProperty, eventDto.getOrganizer());
                    model.add(eventResource, maxParticipantsProperty, Integer.toString(eventDto.getMax_participants()));
                    model.add(eventResource, imageProperty, eventDto.getImage());

                    // Sauvegarder le modèle
                    JenaEngine.saveModel(model, "data/ecodev.owl");

                    return new ResponseEntity<>("Event updated successfully: " + eventDto.getEvent(), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Event not found", HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity<>("Error updating Event: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Error when reading model from ontology", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteEvent")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> deleteEvent(@RequestBody EventDto eventDto) {
        if (model != null) {
            try {
                // Retrieve the URI of the event to delete
                String eventUri = eventDto.getEvent();  // URI passed in the DTO

                // Check that the event exists in the model
                Resource eventResource = model.getResource(eventUri);

                if (eventResource != null) {
                    // If the event has rdf:type Event or is just a NamedIndividual, proceed with removal
                    if (eventResource.hasProperty(RDF.type, model.createResource("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#Event")) ||
                            eventResource.getURI() != null && eventResource.getURI().contains("Event")) {

                        // Remove all properties associated with the event
                        model.removeAll(eventResource, null, null);  // Removes all properties of this resource

                        // Save the model after deletion
                        JenaEngine.saveModel(model, "data/ecodev.owl");

                        return new ResponseEntity<>("Event deleted successfully: " + eventUri, HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>("Event not found or invalid type", HttpStatus.NOT_FOUND);
                    }
                } else {
                    return new ResponseEntity<>("Event not found", HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity<>("Error deleting Event: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Error when reading model from ontology", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

/*
@GetMapping("/getAllEvents")
@CrossOrigin(origins = "http://localhost:3000")
public ResponseEntity<List<EventDto>> getAllEvents() {
    List<EventDto> events = new ArrayList<>();

    if (model != null) {
        try {
            // Créer la ressource Event (type RDF)
            Resource eventType = model.createResource("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#Event");

            // Rechercher toutes les ressources de type "Event"
            StmtIterator eventIterator = model.listStatements(null, RDF.type, eventType);

            // Parcourir toutes les ressources de type Event
            while (eventIterator.hasNext()) {
                Statement stmt = eventIterator.nextStatement();
                Resource eventResource = stmt.getSubject();

                // Extraire les propriétés de l'événement
                String title = eventResource.getProperty(model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#title")).getString();
                String description = eventResource.getProperty(model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#description")).getString();
                String date = eventResource.getProperty(model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#date")).getString();
                String location = eventResource.getProperty(model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#location")).getString();
                String organizer = eventResource.getProperty(model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#organizer")).getString();
                String maxParticipants = eventResource.getProperty(model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#maxParticipants")).getString();
                String image = eventResource.getProperty(model.createProperty("http://www.semanticweb.org/nvsar/ontologies/2024/9/ontologie1#image")).getString();

                // Créer un EventDto pour chaque événement
                EventDto eventDto = new EventDto();
                eventDto.setTitle(title);
                eventDto.setDescription(description);
                eventDto.setDate(date); // Assurez-vous de formater la date correctement si nécessaire
                eventDto.setLocation(location);
                eventDto.setOrganizer(organizer);
                eventDto.setMax_participants(Integer.parseInt(maxParticipants));
                eventDto.setImage(image);

                // Ajouter l'événement à la liste
                events.add(eventDto);
            }
            return new ResponseEntity<>(events, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } else {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
 */




    // Inventory CRUD Operations

}