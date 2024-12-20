# World of Lucky Charms 🍀 

## Overview
*World of Lucky Charms*  is a whimsical and interactive simulation inspired by my love for Lucky Charms cereal! The project began as a class assignment, where we were tasked with refactoring a base simulation written in procedural design. I first refactored this codebase from procedural to object-oriented design, improving the overall structure, scalability, and maintainability of the project. After the refactor, I implemented better pathfinding mechanics using algorithms like A* and Dijkstra to improve how entities navigate the world. Finally, I collaborated with a partner to transform the original simulation into the World of Lucky Charms, where we introduced new entities, landmarks, interactions, and transformations, creating a more dynamic and engaging experience.

## 🎮 Gameplay Instructions
1. **Spawn a Rainbow**:  
   Click on the bottom-left house (coordinates: 21, 25) to spawn a rainbow. When a "dude" steps onto the rainbow, it transforms into a leprechaun.  
2. **Spawn Unicorns**:  
   Click anywhere on the map (except the house at 21, 25) to spawn unicorns.  
3. **Lucky Charms**:  
   - Leprechauns randomly drop **lucky charms**, which unicorns will seek out and consume.  
   - Unicorns consuming charms triggers various transformations:  
     - **Moon Charm** → Becomes a "dude (not full)"  
     - **Star Charm** → Becomes a **fairy**  
     - **Hat Charm** → Becomes a **leprechaun**  
     - **Rainbow Charm** → Becomes an **evil fairy**  
4. **Evil Fairies**:  
   - Evil fairies can consume existing yellow fairies and turn into unicorns.  
   - If no fairies are present, they act like regular fairies.  

### Entity Transformations
- **Dude → Leprechaun**: Steps onto a rainbow.  
- **Unicorn → Various Forms**: Consumes different types of lucky charms to transform.  
- **Evil Fairy**: Consumes yellow fairies to turn into unicorns.  

## ✨ Features
- Dynamic spawning and interaction of entities:
  - Rainbows, leprechauns, unicorns, fairies, and evil fairies.
- Randomized **lucky charm drops** with unique effects.
- Real-time transformations and animations.
- **Custom sprites** for lucky charms, rainbows, and unicorns (created with Piskel).  

## 🛠️ Technical Highlights
- **Custom Sprites**:  
   Created visuals for lucky charms (7 types, 4 frames each), unicorns, and rainbows.  
- **Randomized Charm Drops**:  
   Implemented a random charm generator using a `LuckyCharm` abstract class and subclasses for each charm type.  
- **Efficient Coordinate Mapping**:  
  Utilized a hashmap to manage rainbow coordinates, optimizing rendering performance during gameplay.  
- **Advanced Pathfinding for Entities**:  
  Implemented A* and Dijkstra's algorithms to dynamically map unicorns to the closest lucky charm, as well as other entities to their respective target, ensuring efficient transformations.

## 🎥 Demo
https://github.com/user-attachments/assets/7d1d35cb-aedc-4940-9040-a9fcf84a2a17

## Contributors
- **Sharon Liang**
- **Sara Voong**

Together, we worked on transforming the initial simulation into a dynamic and interactive game.
