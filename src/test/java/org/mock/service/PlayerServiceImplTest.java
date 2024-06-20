package org.mock.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mock.DataProvider;
import org.mock.persistence.Player;
import org.mock.repository.PlayerRepositoryImpl;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceImplTest {
    // Given
    @Mock
    private PlayerRepositoryImpl playerRepository; // Recordar que Mockito trabaja con la dependencia
    @InjectMocks
    private PlayerServiceImpl playerService;

    @Test
    public void testFindAll(){

        // When
        when(playerRepository.findAll()).thenReturn(DataProvider.playerListMock());
        List<Player> result = playerService.findAll();

        // Then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("Lionel Messi", result.get(0).getName());
        assertEquals("Inter Miami", result.get(0).getTeam());
        assertEquals("Delantero", result.get(0).getPosition());
        verify(this.playerRepository).findAll();
    }

    @Test
    public void testFindById(){
        // Given
        Long id = 1L;
        // When
        when(playerRepository.findById(anyLong())).thenReturn(DataProvider.playerMock());
        Player result = playerService.findById(id);

        // Then
        assertNotNull(result);
        assertEquals("Lionel Messi", result.getName());
        assertEquals("Inter Miami", result.getTeam());
        assertEquals("Delantero", result.getPosition());

        verify(this.playerRepository).findById( anyLong() );  //Este 'verify' solo funciona con la dependencia
    }

    @Test
    public void testSave(){
        // Given
        Player player = DataProvider.newPlayerMock();

        // When
        this.playerService.save(player);

        // Then
        ArgumentCaptor<Player> playerArgumentCaptor = ArgumentCaptor.forClass(Player.class);
        verify(this.playerRepository).save( any(Player.class) );
        verify(this.playerRepository).save( playerArgumentCaptor.capture() );
        assertEquals(7L, playerArgumentCaptor.getValue().getId());
        assertEquals("Luis Diaz", playerArgumentCaptor.getValue().getName());
    }

    @Test
    public void deleteById(){
        // Given
        Long id = 1L;

        // When
        this.playerService.deleteById(id);

        // Then
        ArgumentCaptor<Long> longArgumentCaptor = ArgumentCaptor.forClass(Long.class);
        verify(this.playerRepository).deleteById( anyLong() );
        verify(this.playerRepository).deleteById( longArgumentCaptor.capture() );
        assertEquals(1L, longArgumentCaptor.getValue());

    }

}
