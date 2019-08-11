package com.ajit.genomcitywar;

import java.util.ArrayList;

public class TestWar {

	
	public static void main(String[] args) {
		int numberOfGenomArchers = 4;
		int numberOfEnemies = 2;
		
		Marina marina = new Marina();
		
		ArrayList<GenomArcher> genomArchers = new ArrayList<GenomArcher>();
		for (int i = 0; i < numberOfGenomArchers; i++) {
			GenomArcher genomArcher = new GenomArcher(i+1);
			genomArcher.putArrowsInMarina(marina);
			genomArchers.add(genomArcher);
		}
		GenomArmy genomArmy = new GenomArmy(genomArchers);
		
		ArrayList<Enemy> enemies = new ArrayList<Enemy>();
		for (int i = 0; i < numberOfEnemies; i++) {
			Enemy enemy = new Enemy(i+1);
			enemies.add(enemy);
		}
		EnemyArmy enemyArmy = new EnemyArmy(enemies);
		
		genomArmy.doFight(enemyArmy);

	}

}
