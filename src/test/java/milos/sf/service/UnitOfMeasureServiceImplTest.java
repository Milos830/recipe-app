package milos.sf.service;

import milos.sf.Domain.UnitOfMeasure;
import milos.sf.commands.UnitOfMeasureCommand;
import milos.sf.converters.UnitOfMeasureToUnitOfMeasureCommand;
import milos.sf.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UnitOfMeasureServiceImplTest {

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
    UnitOfMeasureService service;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        service = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, unitOfMeasureToUnitOfMeasureCommand);

    }

    @Test
    void listAllUoms() throws Exception {
        //given
        Set<UnitOfMeasure> unitOfMeasure = new HashSet<>();
        UnitOfMeasure oum1 = new UnitOfMeasure();
        oum1.setId(1L);
        unitOfMeasure.add(oum1);

        UnitOfMeasure oum2 = new UnitOfMeasure();
        oum2.setId(2L);
        unitOfMeasure.add(oum2);

        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasure);

        //when
        Set<UnitOfMeasureCommand> commands = service.listAllUoms();

        //then
        assertEquals(2, commands.size());
        verify(unitOfMeasureRepository, times(1)).findAll();
    }
}