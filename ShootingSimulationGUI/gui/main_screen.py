from base.colors import *
from base.dimensions import Dimensions
from base.important_variables import *
from base.utility_functions import percentages_to_numbers
from base.velocity_calculator import VelocityCalculator
from gui.shooting_screen import ShootingScreen
from gui_components.button import Button
from gui_components.grid import Grid
from gui_components.screen import Screen
from gui_components.text_box import TextBox
from logic.file_reader import FileReader


class MainScreen(Screen):
    """The main screen where everything is displayed"""

    start_screen = None
    current_sub_screen = None
    screen_number = 0
    sub_screens = []
    start_distances = []
    end_distances = []
    height_used_up = 0
    delta_angles = []
    screen_number_field = TextBox("Screen Number: ", 20, False, brown, white)
    start_distance_field = TextBox("Start Distance: 15", 20, False, purple, white)
    end_distance_field = TextBox("New Distance: ", 20, False, magenta, white)
    delta_angle_field = TextBox("Delta Angle: ", 20, False, black, white)
    components = [start_distance_field, end_distance_field, delta_angle_field, screen_number_field]

    def __init__(self):
        """Initializes the object"""

        # Creates a little buffer between sub screens and this screen
        grid = Grid(Dimensions(0, 0, screen_length, main_screen_height), None, 2, True)
        grid.turn_into_grid(self.components, None, None)

        self.height_used_up = grid.dimensions.bottom + main_screen_buffer
        self.create_sub_screens()
        self.current_sub_screen = self.sub_screens[self.screen_number]

    def create_sub_screens(self):
        """Creates all the sub screens"""

        file_reader = FileReader("C:\\Users\\mdrib\\Downloads\\Robotics\\ShootingSimulation\\ShootingSimulationGUI\\data.txt")
        number_of_tests = file_reader.get_int("numberOfTests")

        for x in range(number_of_tests):
            file_start = f"test_number{x + 1}"
            #
            # if x == file_reader.get_int("skipped_index"):
            #     continue

            self.delta_angles.append(file_reader.get_double(f"{file_start}delta_angle"))
            self.start_distances.append(file_reader.get_double(f"{file_start}start_distance"))
            self.end_distances.append(file_reader.get_double(f"{file_start}end_distance"))

            start_xy = file_reader.get_number_list(f"{file_start}start_point")
            end_xy = file_reader.get_number_list(f"{file_start}end_point")
            sub_screen = ShootingScreen(self.height_used_up, start_xy, end_xy)
            self.sub_screens.append(sub_screen)

    def run(self):
        self.current_sub_screen = self.sub_screens[self.screen_number]
        self.screen_number_field.text = f"Screen Number: {self.screen_number + 1}/{len(self.sub_screens)}"
        self.delta_angle_field.text = f"Delta Angle: {self.delta_angles[self.screen_number]}"
        self.end_distance_field.text = f"End Distance: {self.end_distances[self.screen_number]}"
        self.start_distance_field.text = f"Start Distance: {self.start_distances[self.screen_number]}"

    def get_components(self):
        """returns: List of Component; all the components of the screen that should be displayed"""

        return self.components + self.current_sub_screen.get_components()

    def switch_screen_left(self):
        """Goes to the previous screen"""


        if self.screen_number != 0:
            self.screen_number -= 1

        else:
            self.screen_number = len(self.sub_screens) - 1

    def switch_screen_right(self):
        """Goes to the next screen"""

        if self.screen_number == len(self.sub_screens) - 1:
            self.screen_number = 0

        else:
            self.screen_number += 1

