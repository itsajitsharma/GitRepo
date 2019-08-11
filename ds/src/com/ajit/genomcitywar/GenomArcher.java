package com.ajit.genomcitywar;

import java.util.ArrayList;
import java.util.Iterator;

public class GenomArcher {
	
	int archerId;
	ArrayList<Arrow> arrows;
	

	public GenomArcher(int archerId){
		this.archerId = archerId;
		getArrows(archerId);
	}
	
	private void getArrows(int archerId) {
		ArrayList<Arrow> arrows= new ArrayList<Arrow>();
		for (int i = 1; i <= archerId ; i++) {
			Arrow arrow = new Arrow();
			arrows.add(arrow);
		}
		this.arrows = arrows;
	}

	public void putArrowsInMarina(Marina marina){
		marina.processArrows(arrows);
	}
	
	public void fireArrows(EnemyArmy enemyArmy, GenomArmy genomArmy){
		ArrayList<Enemy> enemies = enemyArmy.getEnemyArmy();
		
		while(arrows.size()>0) {
			int remainingEnemies = enemyArmy.getRemaingEnemies();
			if(remainingEnemies == 0)
				break;
			int totalEnemies = enemyArmy.getTotalEnemies();
			Enemy enemy = enemies.get(totalEnemies - remainingEnemies);
			int numberOfArrowsNeeded = enemy.getNumberOfArrowsForDeath();
			while(arrows.size() > 0 && numberOfArrowsNeeded > 0){
				Arrow arrow = arrows.get(0);
				int attack = arrow.getAttack();
				int arrowsFired = genomArmy.getNumberOfArrowFired() + 1;
				if(arrowsFired % 7 == 0)
					numberOfArrowsNeeded+=2;
				else
					numberOfArrowsNeeded = numberOfArrowsNeeded - attack;
				arrows.remove(0);
				genomArmy.setNumberOfArrowFired(arrowsFired);
			}
			if(numberOfArrowsNeeded > 0){
				enemy.setNumberOfArrowsForDeath(numberOfArrowsNeeded);
			}else{
				enemyArmy.setRemaingEnemies(remainingEnemies - 1);
			}
		}
		
	}

}
