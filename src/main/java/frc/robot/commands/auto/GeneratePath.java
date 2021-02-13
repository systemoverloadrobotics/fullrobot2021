package frc.robot.commands.auto;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;

public class GeneratePath {

    private enum  SkillPath {
        BARREL, 
        SLALOM, 
        BOUNCE
    }; 
    private String trajectoryJSON;
    private Trajectory trajectory; 

    public GeneratePath(){       
    }

    public void doPath(int which){
        SkillPath skillPath = SkillPath.values()[which];
        if(which >= 0 && which < 3){
            switch(skillPath){
                case BARREL:
                    trajectoryJSON = "paths/Barrel.wpilib.json";
                    trajectory = new Trajectory();{
                        try {
                            Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
                            trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
                        } catch (IOException ex) {  
                        DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
                        }
                    }     
                    break;
                case SLALOM:
                    trajectoryJSON = "paths/Slalom.wpilib.json";
                    trajectory = new Trajectory();{
                        try {
                            Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
                            trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
                        } catch (IOException ex) {  
                            DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
                        }
                    }
                    break;
                case BOUNCE:
                    trajectoryJSON = "paths/Bounce.wpilib.json";
                    trajectory = new Trajectory();{
                        try {
                            Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
                            trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
                        } catch (IOException ex) {  
                            DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
                        }
                    }
                    break;
                default:
                    break;
            }            
        }
    }   
        

}
