package org.mock;

import org.mock.persistence.Player;
import org.mock.repository.PlayerRepositoryImpl;
import org.mock.service.PlayerServiceImpl;

public class Main {
    public static void main(String[] args) {

        PlayerRepositoryImpl playerRepository = new PlayerRepositoryImpl();

        // Vamos a testear mi PlayerServiceImpl, pero necesito una dependencia que es PlayerRepositoryImpl
        PlayerServiceImpl playerService = new PlayerServiceImpl(playerRepository);

        // System.out.println(playerService.findAll());

        //System.out.println(playerService.findById(1L));

        //playerService.deleteById(1L);
        //System.out.println(playerService.findAll());

        Player player = new Player(7L, "Luis Díaz", "Liverpool", "Delantero");
        playerService.save(player);
        System.out.println(playerService.findAll());

    }
}