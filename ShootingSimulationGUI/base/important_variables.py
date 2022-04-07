from gui_components.window import Window
from base.function_runner import FunctionRunner
from logic.file_reader import FileReader
from utillities.changer import Changer

screen_height = 600
screen_length = screen_height
main_screen_total_percentage = .2
main_screen_height = (main_screen_total_percentage - .05) * screen_height
main_screen_buffer = .05 * screen_height
background_color = (70, 70, 70)
game_window = Window(screen_length, screen_height, "Pong Reloaded", background_color)
function_runner = FunctionRunner()
changer = Changer()

file_reader = FileReader("C:\\Users\\mdrib\\Downloads\\Robotics\\ShootingSimulation\\data.txt")
data_reader = FileReader("C:\\Users\\mdrib\\Downloads\\Robotics\\ShootingSimulation\\ShootingSimulationGUI\\data.txt")
hub_radius = file_reader.get_int("hub_height")
distance_from_hub = file_reader.get_int("distance_from_hub")
x_points_from_center = file_reader.get_int("x_points_from_center")
y_points_from_center = file_reader.get_int("y_points_from_center")
hub_point = data_reader.get_number_list("hubPoint")
robot_start_point = data_reader.get_number_list("robotStart")


space_taken_up_by_main_screen = screen_height * main_screen_total_percentage
scalar_divider = max(x_points_from_center, y_points_from_center) * 2 + 1 + hub_radius + distance_from_hub
scalar = (screen_height - space_taken_up_by_main_screen) / scalar_divider
hub_radius *= scalar

