package ru.Lab6.common.builder;

import ru.Lab6.common.collectionClass.*;

// todo collections
// todo type erasure
// java io & nio

public interface Builder {
    Builder generationId();
    Builder fixCreationDate(String creationDate);
    Builder fixId(Long id);
    Builder fixName(String name);
    Builder fixCoordinates(Coordinates coordinates);
    Builder fixLocationFrom(LocationFrom from);
    Builder fixLocationTo(LocationTo to);
    Builder fixDistance(Long distance);
    Route build();
}
