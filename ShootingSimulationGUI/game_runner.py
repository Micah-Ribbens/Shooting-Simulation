import cProfile

import pygame.display

from base.engines import CollisionsFinder
from base.path import *
from base.utility_classes import HistoryKeeper
from base.important_variables import *
import time
from base.velocity_calculator import VelocityCalculator
from gui.main_screen import MainScreen
import re

main_screen = MainScreen()
game_window.add_screen(main_screen)
game_window.set_screen_visible(main_screen, True)

left_key_clicked_last_cycle = False
right_key_clicked_last_cycle = False

while True:
    start_time = time.time()
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()

    game_window.run()
    controls = pygame.key.get_pressed()
    left_key_held_in = controls[pygame.K_LEFT]
    right_key_held_in = controls[pygame.K_RIGHT]

    if left_key_held_in and not left_key_clicked_last_cycle:
        main_screen.switch_screen_left()

    if right_key_held_in and not right_key_clicked_last_cycle:
        main_screen.switch_screen_right()

    left_key_clicked_last_cycle = left_key_held_in
    right_key_clicked_last_cycle = right_key_held_in
    CollisionsFinder.objects_to_data = {}
    function_runner.run()
    changer.run_changes()
    HistoryKeeper.last_time = VelocityCalculator.time
    VelocityCalculator.time = time.time() - start_time

