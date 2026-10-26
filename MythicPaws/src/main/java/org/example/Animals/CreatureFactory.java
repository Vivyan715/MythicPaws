package org.example.Animals;

import org.example.Enums.CreatureType;

//állatok példányosítására
public class CreatureFactory {

    public Creature spawnCreature(CreatureType type) {
        switch (type) {
            case CATASHTROPHE:
                return new Catashtrophe();

            default:
                throw new IllegalArgumentException("Unknown creature type: " + type);

                //"Az IllegalArgumentException egy beépített Java hibaüzenet (kivétel), amely akkor lép fel, ha egy metódus érvénytelen vagy nem megengedett paramétert (bemenő adatot) kap."
        }
    }
}
