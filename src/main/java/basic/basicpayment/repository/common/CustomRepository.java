package basic.basicpayment.repository.common;

import basic.basicpayment.exception.validation.ItemValidator;

public interface CustomRepository <ENTITY, ID> {

    ENTITY findOneOrNull(ID id);

    default ENTITY findOneOrThrow(ID id) {
        ENTITY entity = findOneOrNull(id);
        ItemValidator.validateNotFound(entity, id);
        return entity;
    }

}
