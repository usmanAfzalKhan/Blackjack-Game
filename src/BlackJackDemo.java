/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Saranjeet
 */

import java.util.Scanner;

public class BlackJackDemo {

	public static void main(String[] args){
		
		System.out.println("Welcome to our demo of a classic BlackJack ");
		
		//This will be the deck the dealer holds
		Deck playingDeck = new Deck();
		playingDeck.createFullDeck();
		playingDeck.shuffle();
		
		//This will be the cards the player has in their hand
		Deck playerCards = new Deck();
		
		double playerMoney = 100.0;
		
		Deck dealerCards = new Deck();
		
		
		Scanner userInput = new Scanner(System.in);
		
		
while(playerMoney>0){
	
	System.out.println("You have $" + playerMoney + ", how much would you like to bet?");
	double playerBet = userInput.nextDouble();
	boolean endRound = false;
	if(playerBet > playerMoney){
		
		System.out.println("You cannot bet more money than you currently have .");
		break;
	}
	
	System.out.println("Dealing your cards..");
	//Player gets two cards
	playerCards.draw(playingDeck);
	playerCards.draw(playingDeck);
	
	//Dealer gets two cards
	dealerCards.draw(playingDeck);
	dealerCards.draw(playingDeck);
			
			//create a loop for drawing new cards
			while(true)
			{
				//Display player cards
				System.out.println("Your Hand:" + playerCards.toString());
				
				//Display Value
				System.out.println("Your hand has a total value of: " + playerCards.cardsValue());
				
				//Display dealer cards
				System.out.println("Dealer Hand: " + dealerCards.getCard(0).toString());
				
				
				System.out.println("Would you like to (1)Hit or (2)Stand");
				int response = userInput.nextInt();	
				//if They  chose to hit
				if(response == 1){
					playerCards.draw(playingDeck);
					System.out.println("You draw a:" + playerCards.getCard(playerCards.deckSize()-1).toString());
					//Game is Bust if the total value goes over 21
					if(playerCards.cardsValue() > 21){
						System.out.println("Bust. Your total value of cards is : " + playerCards.cardsValue());
						playerMoney -= playerBet;
						endRound = true;
						break;
					}
				}
				
				//If they chose to Stand
				if(response == 2){
					break;
				}
				
			}
				
			//Reveal Dealer Cards
			System.out.println("Dealer Cards:" + dealerCards.toString());
			//See if dealer has more points than player
			if((dealerCards.cardsValue() > playerCards.cardsValue())&&endRound == false){
				System.out.println("Dealer beats you " + dealerCards.cardsValue() + " to " + playerCards.cardsValue());
				playerMoney -= playerBet;
				endRound = true;
			}
			//Dealer hits at 16 stands at 17
			while((dealerCards.cardsValue() < 17) && endRound == false){
				dealerCards.draw(playingDeck);
				System.out.println("Dealer draws: " + dealerCards.getCard(dealerCards.deckSize()-1).toString());
			}
			//Display value of dealer
			System.out.println("Dealers hand value: " + dealerCards.cardsValue());
			//Determine if dealer busted
			if((dealerCards.cardsValue()>21)&& endRound == false){
				System.out.println("Dealer Busts. You win!");
				playerMoney += playerBet;
				endRound = true;
			}
			//Determine if push(which means both player and dealer has equal total)
			if((dealerCards.cardsValue() == playerCards.cardsValue()) && endRound == false){
				System.out.println("Push.");
				endRound = true;
			}
			//Determine if player wins
			if((playerCards.cardsValue() > dealerCards.cardsValue()) && endRound == false){
				System.out.println("You win the hand.");
				playerMoney += playerBet;
				endRound = true;
			}
			else if(endRound == false) //dealer wins
			{
				System.out.println("Dealer wins.");
				playerMoney -= playerBet;
			}

			
			playerCards.moveAllToDeck(playingDeck);
			dealerCards.moveAllToDeck(playingDeck);
			System.out.println("End of Hand.");
			
		}
		//Game is over
		System.out.println("Game over! Try Again! ");
		
		
		userInput.close();
		
	}
	
	
}
